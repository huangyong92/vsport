package sport.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatVerifyUtil {

    public static boolean isPhone(String phone) {
        Pattern pattern = Pattern.compile("^1[34578][0-9]{9}");

        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
