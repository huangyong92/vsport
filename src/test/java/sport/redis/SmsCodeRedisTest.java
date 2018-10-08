package sport.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sport.redis.sms.SmsCodeEntity;
import sport.redis.sms.SmsCodeRedis;
import sport.util.Constant;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsCodeRedisTest {

    @Autowired
    private SmsCodeRedis smsCodeRedis;

    private String mobile = "13575732183";
    private String smsCode = "555";

    @Test
    public void saveSmsCode() {
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    smsCodeRedis.saveSmsCode(mobile, smsCode);
                }
            }).start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                smsCodeRedis.saveSmsCode("1111", smsCode);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                smsCodeRedis.saveSmsCode("2222", smsCode);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                smsCodeRedis.saveSmsCode("3333", smsCode);
            }
        }).start();

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    smsCodeRedis.saveSmsCode("4444", smsCode);
                }
            }).start();
        }

        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getSmsCode() {
        SmsCodeEntity smsCodeEntity = smsCodeRedis.getSmsCode(mobile);

        String smsCode = "";
        if (smsCodeEntity != null) {
            smsCode = smsCodeEntity.getSmsCode();
        }

        assertNotEquals("", smsCode);
    }

    @Test
    public void getSmsCodes() {
        List<SmsCodeEntity> smsCodeEntities = smsCodeRedis.getSmsCodes(mobile, 4);

        assertNotNull(smsCodeEntities);
    }

    @Test
    public void updateSmsCode() {
        for (int i = 0; i < 500; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    smsCodeRedis.updateSmsCode(mobile, 1539003460845l, Constant.HasVeriry);
                }
            }).start();
        }

        try {
            CountDownLatch countDownLatch = new CountDownLatch(1);
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}