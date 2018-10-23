package sport.util;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class FormatVerifyUtil {

    public boolean isPhone(String phone) {
        Pattern pattern = Pattern.compile("^1[34578][0-9]{9}");

        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
