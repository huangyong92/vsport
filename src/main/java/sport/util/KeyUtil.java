package sport.util;

public class KeyUtil {

    public static String getLiveCourseKey(int publisherId, long startTime) {
        return String.valueOf(publisherId) + startTime;
    }
}
