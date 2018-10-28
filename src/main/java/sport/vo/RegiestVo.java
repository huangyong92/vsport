package sport.vo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegiestVo {

    @NotBlank(message = "appId不能为空")
    public String appId;

    @NotBlank(message = "手机号必填")
    public String phone;

    @NotBlank(message = "请填写密码")
    public String password;

    @NotBlank(message = "请为自己起个名字")
    public String nickName;

    @NotNull(message = "请填写性别")
    @Max(1)
    public int gender;

    public String liveCity;

    public String sprotTarget;
}
