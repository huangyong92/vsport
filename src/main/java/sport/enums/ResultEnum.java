package sport.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    OK(0, "ok"),
    SMS_DAY_LIMIT(-10000, "您今天短信已用完"),
    SMS_SHORT_INTERVAL(-10001, "请不要频繁发送"),
    SMS_TYPE_ERROR(-10002, "请求短信类型错误"),
    SMS_MOBILE_ERROR(-10003, "手机号码不合适"),
    SMS_SEND_ERROR(-10004, "短信发送失败,请稍后再试"),
    SMS_INVAILD_CODE(-10005, "无效的验证码"),
    ;

    private int code;
    private String msg;

    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
