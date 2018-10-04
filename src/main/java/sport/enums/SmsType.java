package sport.enums;

import lombok.Getter;

@Getter
public enum SmsType {
    LOGIN(0, "login"),
    FIND_PASSWORD(1, "password")
    ;

    int code;
    String type;

    SmsType(int code, String type) {
        this.code = code;
        this.type = type;
    }
}
