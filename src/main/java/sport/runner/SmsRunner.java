package sport.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sport.util.Constant;
import sport.util.SmsUtil;

@Component
public class SmsRunner implements CommandLineRunner {

    @Autowired
    private SmsUtil smsUtil;

    @Override
    public void run(String... args) throws Exception {
        smsUtil.configSms();
        smsUtil.setAccount(Constant.SMS_ACCOUNT, Constant.SMS_PASSWORD);
    }
}
