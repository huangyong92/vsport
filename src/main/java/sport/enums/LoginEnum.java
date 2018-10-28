package sport.enums;

import lombok.Getter;

@Getter
public enum  LoginEnum {
    LOGIN("1"),
    LOGOUT("0")
    ;

    private String statu;

    LoginEnum(String statu) {
        this.statu = statu;
    }
}
