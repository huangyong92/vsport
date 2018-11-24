package sport.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "用户信息")
public class RegiestVo {

    @ApiModelProperty(name = "appId", value = "推送对应的clientId",
    dataType = "string", required = true)
    @NotBlank(message = "appId不能为空")
    public String appId;

    @ApiModelProperty(name = "phone", value = "手机号码",
            dataType = "string", required = true)
    @NotBlank(message = "手机号必填")
    public String phone;

    @ApiModelProperty(name = "password", value = "密码不能超过32位", required = true,
    dataType = "string")
    @NotBlank(message = "请填写密码")
    public String password;

    @ApiModelProperty(name = "nickName", value = "昵称不能超过64字符", required = true)
    @NotBlank(message = "请为自己起个名字")
    public String nickName;

    @ApiModelProperty(name = "gender", value = "0:女性 1:男性",
            required = true, dataType = "int")
    @NotNull(message = "请填写性别")
    @Max(1)
    public int gender;

    @ApiModelProperty(name = "liveCity", value = "居住城市",
            required = false)
    public String liveCity;

    @ApiModelProperty(name = "sprotTarget", value = "运动目标",
            required = false)
    public String sprotTarget;
}
