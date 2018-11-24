package sport.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "修改用户基本信息")
public class UserPrimaryInfoVo {

    @ApiModelProperty(value = "头像", name = "avatar")
    public String avatar;

    @ApiModelProperty(value = "昵称", name = "nickName")
    public String nickName;

    @ApiModelProperty(value = "居住城市", name = "livingCity")
    public String livingCity;

    @ApiModelProperty(value = "运动目标", name = "sportTarget")
    public String sportTarget;

}
