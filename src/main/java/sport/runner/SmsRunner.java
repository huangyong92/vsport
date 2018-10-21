package sport.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sport.util.Constant;
import sport.util.PushUtil;
import sport.util.SmsUtil;
import sport.util.TokenUtil;

@Component
public class SmsRunner implements CommandLineRunner {

    @Autowired
    private SmsUtil smsUtil;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public void run(String... args) throws Exception {
        smsUtil.configSms();
        smsUtil.setAccount(Constant.SMS_ACCOUNT, Constant.SMS_PASSWORD);

        PushUtil.createGtPush(Constant.PUSH_APP_KEY,
                Constant.PUSH_MASTER_SECRET,
                Constant.PUSH_URL);

//        tokenUtil.getConsumer();
    }
}
