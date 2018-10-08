package sport.util;

import org.springframework.stereotype.Component;
import sport.enums.SmsType;
import sport.redis.sms.SmsContent;

import java.util.Random;

@Component
public class SmsContentUtil {

    //每秒变化一次
    private String getCode() {
        long timeStamp = System.currentTimeMillis();
        long timeSec = timeStamp / 1000;


        String seed = String.valueOf(timeSec);
        int seedLen = seed.length();

        return seed.substring(seedLen - 4);
    }

    public SmsContent getContent(String contentType) {
        String code = getCode();

        StringBuffer contentBuffer = new StringBuffer();
        if (SmsType.LOGIN.getType().equals(contentType)) {
            return
                    new SmsContent(code,
                            contentBuffer
                                    .append("您的注册验证码是")
                                    .append(code)
                                    .append("，在15分钟内输入有效。如非本人操作请忽略此短信。")
                                    .toString());

        }

        if (SmsType.FIND_PASSWORD.getType().equals(contentType)) {
            return
                    new SmsContent(code,
                            contentBuffer
                                    .append("验证码为")
                                    .append(code)
                                    .append("，您正在修改登录密码，请确认是本人操作。")
                                    .toString());
        }

        return null;
    }

}
