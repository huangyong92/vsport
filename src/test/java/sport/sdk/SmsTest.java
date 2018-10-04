package sport.sdk;

import com.montnets.mwgate.smsutil.SmsSendConn;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sport.enums.SmsType;
import sport.redis.sms.SmsContent;
import sport.util.Constant;
import sport.util.SmsContentUtil;
import sport.util.SmsUtil;
import sport.util.TimeUtil;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsTest {

    @Autowired
    private SmsUtil smsUtil;

    @Autowired
    private SmsContentUtil smsContentUtil;

    @Autowired
    private TimeUtil timeUtil;

    @Test
    public void sigleSend() {
//        smsUtil.configSms();
//        smsUtil.setAccount(Constant.SMS_ACCOUNT, Constant.SMS_PASSWORD);

        SmsContent smsContent = smsContentUtil.getContent(SmsType.LOGIN.getType());
        SmsSendConn smsSendConn = smsUtil.getSmsSendConn();
        smsUtil.sigleSend(smsSendConn, "13575732183", smsContent.getContent());
    }
}
