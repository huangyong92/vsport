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
        smsCodeRedis.saveSmsCode(mobile, smsCode);
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
        smsCodeRedis.updateSmsCode(mobile, 1538057886061l, Constant.HasVeriry);
    }
}