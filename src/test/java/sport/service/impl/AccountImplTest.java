package sport.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sport.enums.SmsType;
import sport.service.AccountService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountImplTest {

    @Autowired
    private AccountService mAccountService;

    private String mobile = "13575732183";

    @Test
    public void isDayLimit() {
        mAccountService.getSmsCodes(mobile);
        //todo 这个限制的话，必须是今天的，如果不做日期判断的话，必须在晚上12点把数据都清除
        boolean isDayLimit = mAccountService.isDayLimit(mobile);

        assertEquals(false, isDayLimit);
    }

    @Test
    public void isIntervalOk() {
        boolean isIntervalOk = mAccountService.isIntervalOk(mobile);
        assertEquals(false, isIntervalOk);
    }

    @Test
    public void sendSmsCode() {
        int result = mAccountService.sendSmsCode(mobile, SmsType.LOGIN.getType());
        assertEquals(0, result);
    }

    @Test
    public void verifySmsCode() {
    }
}