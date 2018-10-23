package sport.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sport.enums.SmsType;
import sport.service.SmsService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmsImplTest {

    @Autowired
    private SmsService mSmsService;

    private String mobile = "13575732183";

    @Test
    public void isDayLimit() {
        mSmsService.getSmsCodes(mobile);
        //todo 这个限制的话，必须是今天的，如果不做日期判断的话，必须在晚上12点把数据都清除
        boolean isDayLimit = mSmsService.isDayLimit(mobile);

        assertEquals(false, isDayLimit);
    }

    @Test
    public void isIntervalOk() {
        boolean isIntervalOk = mSmsService.isIntervalOk(mobile);
        assertEquals(false, isIntervalOk);
    }

    @Test
    public void sendSmsCode() {
        int result = mSmsService.sendSmsCode(mobile, SmsType.LOGIN.getType());
        assertEquals(0, result);
    }

    @Test
    public void verifySmsCode() {

        int code = mSmsService.verifySmsCode(mobile, "3816");
        assertEquals(0, code);
    }
}