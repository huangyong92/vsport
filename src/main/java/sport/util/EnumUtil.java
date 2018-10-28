package sport.util;

import sport.enums.BaseEnum;

public class EnumUtil {

    public static <T extends BaseEnum> T getEnum(Class<T> tClass, Integer code) {
        T[] enums = tClass.getEnumConstants();

        System.out.println(enums.length);

        for (T enumObjec :
                enums) {
            if (enumObjec.getEnumCode() == code) {
                return enumObjec;
            }
        }

        return null;
    }
}
