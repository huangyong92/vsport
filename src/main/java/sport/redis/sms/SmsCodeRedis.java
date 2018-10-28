package sport.redis.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;
import sport.util.Constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实现验证码逻辑：
 * 同一个手机号一天只能收取4条验证码
 * 同一个手机号发送验证码最小间隔为5分钟
 * 一个验证码15分钟内验证为有效
 * 验证码存活24小时
 */
@Component
public class SmsCodeRedis {

    @Autowired
    private JedisPool mJedisPool;

    private String getMobileKey(String mobile) {
        return "mobile:" + mobile;
    }

    private String getSmsCodeKey(String mobile, String time) {
        return mobile + ":" + time;
    }

    //内部调用
    private SmsCodeEntity getSmsCode(Jedis jedis, String mobile, String sendTime) {
        String smsCodeKey = getSmsCodeKey(mobile, sendTime);
        String smsCode = jedis.hget(smsCodeKey, "code");
        String isVerify = jedis.hget(smsCodeKey, "isVerify");

        if (smsCode == null || isVerify == null) {
            return null;
        }

        return new SmsCodeEntity(mobile, smsCode,
                Long.valueOf(sendTime),
                (Integer.valueOf(isVerify) > 0? true : false)
        );
    }

    public void saveSmsCode(String mobile, String smsCode) {
        Jedis jedis = mJedisPool.getResource();
        long nowTime = System.currentTimeMillis();

        String mobileKey = getMobileKey(mobile);
        String nowTimeStr = String.valueOf(nowTime);


        jedis.watch(mobileKey);
        Transaction transaction = jedis.multi();
        transaction.lpush(mobileKey, nowTimeStr);
        transaction.exec();
//        jedis.lpush(mobileKey, nowTimeStr);

        if (jedis.llen(mobileKey) == Constant.SMS_DAY_COUNT + 1) {
            jedis.rpop(mobileKey);
        }

        String smsCodeKey = getSmsCodeKey(mobile , nowTimeStr);
        Map<String, String> smsValue = new HashMap<>();
        smsValue.put("code", smsCode);
        smsValue.put("isVerify", Constant.NotVeriry);
        jedis.hmset(smsCodeKey, smsValue);
        jedis.expire(smsCodeKey, 24 * 3600);

        if (jedis != null) {
            jedis.close();
        }
    }

    public SmsCodeEntity getSmsCode(String mobile) {
        Jedis jedis = mJedisPool.getResource();
        String sendTime = jedis.lindex(getMobileKey(mobile), 0);

        if (sendTime == null) {
            return null;
        }

        return getSmsCode(jedis, mobile, sendTime);
    }

    public List<SmsCodeEntity> getSmsCodes(String mobile, int count) {
        Jedis jedis = mJedisPool.getResource();
        String mobileKey = getMobileKey(mobile);

        List<SmsCodeEntity> smsCodeList = new ArrayList<>();
        List<String> recentSmsTime = jedis.lrange(mobileKey, 0, count - 1);

        for (String time:
             recentSmsTime) {
            SmsCodeEntity smsCodeEntity = getSmsCode(jedis, mobile, time);
            smsCodeList.add(smsCodeEntity);
        }
        if (jedis != null) {
            jedis.close();
        }

        return smsCodeList;
    }

    public boolean updateSmsCode(String mobile, long time, String isVeriry) {
        Jedis jedis = mJedisPool.getResource();
        String smsCodeKey = getSmsCodeKey(mobile, String.valueOf(time));

        Boolean isExist = jedis.hexists(smsCodeKey, "isVerify");

        //todo 不存在如何处理
        if (isExist) {
            jedis.watch(smsCodeKey);
            Transaction transaction = jedis.multi();
            transaction.hset(smsCodeKey, "isVerify", isVeriry);

            transaction.exec();
            if (jedis != null) {
                jedis.close();
            }
            return true;
        } else {
            if (jedis != null) {
                jedis.close();
            }
            return false;
        }
    }
}
