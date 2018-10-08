package sport.service.impl;

import com.montnets.mwgate.smsutil.SmsSendConn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sport.enums.ResultEnum;
import sport.enums.SmsError;
import sport.redis.sms.SmsCodeEntity;
import sport.redis.sms.SmsCodeRedis;
import sport.redis.sms.SmsContent;
import sport.service.AccountService;
import sport.util.Constant;
import sport.util.SmsContentUtil;
import sport.util.SmsUtil;
import sport.util.TimeUtil;

import java.util.Date;
import java.util.List;

@Service
public class AccountImpl implements AccountService {

    @Autowired
    private SmsCodeRedis mSmsCodeRedis;

    @Autowired
    private SmsUtil mSmsUtil;

    @Autowired
    private SmsContentUtil mSmsContentUtil;

    @Autowired
    private TimeUtil mTimeUtil;

    private List<SmsCodeEntity> mSmsCodeEntityList;


    @Override
    public void getSmsCodes(String mobile) {
        mSmsCodeEntityList =
                mSmsCodeRedis.getSmsCodes(mobile, Constant.SMS_DAY_COUNT);
    }

    //todo 一天只能发送4条,假如多线程刷这个接口，会不会出问题？
    //todo 看看redis能够针对一个key加锁
    @Override
    public boolean isDayLimit(String mobile) {
        if (mSmsCodeEntityList.size() < Constant.SMS_DAY_COUNT) {
            return false;
        }

        SmsCodeEntity compareCode =
                mSmsCodeEntityList.get(Constant.SMS_DAY_COUNT - 1);

        long dayBeginTime = mTimeUtil.getDayBeginTime(new Date());
        return mTimeUtil.compareTime(compareCode.sendTime, dayBeginTime);
    }

    //两次验证码时间间隔必须大于3分钟
    @Override
    public boolean isIntervalOk(String mobile) {
        SmsCodeEntity smsCodeEntity = mSmsCodeRedis.getSmsCode(mobile);
        long currentTime = System.currentTimeMillis();

        long intervalTime = currentTime - smsCodeEntity.sendTime;
        int reSendInterval = 3 * 60 * 1000;
        return mTimeUtil.compareTime(intervalTime, reSendInterval);
    }

    @Override
    public int sendSmsCode(String mobile, String smsType) {
        //发送验证码
        SmsContent smsContent = mSmsContentUtil.getContent(smsType);
        if (smsContent == null) {
            return ResultEnum.SMS_TYPE_ERROR.getCode();
        }

        SmsSendConn smsSendConn = mSmsUtil.getSmsSendConn();
        int result = mSmsUtil.sigleSend(smsSendConn, mobile, smsContent.getContent());
        if (result == SmsError.MOBILE_ERROR.getCode()) {
            return ResultEnum.SMS_MOBILE_ERROR.getCode();
        } else if (result < 0) {
            return ResultEnum.SMS_SEND_ERROR.getCode();
        }

        //保存验证码
        mSmsCodeRedis.saveSmsCode(mobile, smsContent.getCode());
        return 0;
    }

    @Override
    public int verifySmsCode(String mobile, String smsCode) {
        //验证码有效期为15分钟
        SmsCodeEntity smsCodeEntity = mSmsCodeRedis.getSmsCode(mobile);
        long currentTime = System.currentTimeMillis();

        long intervalTime = currentTime - smsCodeEntity.sendTime;
        int validMilliSec = 15 * 60 * 1000;
        boolean isInvalid = mTimeUtil.compareTime(intervalTime, validMilliSec);
        //开始验证
        if (isInvalid || smsCodeEntity.isVeriry
                || !smsCode.equals(smsCodeEntity.smsCode)) {
            return ResultEnum.SMS_INVAILD_CODE.getCode();
        }

        //修改状态
        mSmsCodeRedis.updateSmsCode(mobile, smsCodeEntity.sendTime, Constant.HasVeriry);

        return 0;
    }
}
