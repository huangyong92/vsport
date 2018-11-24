package sport.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "体型数据")
public class BodilyForm {

    public Integer userId;

    public Long recordTime;

    @ApiModelProperty(value = "胸围", name = "bust")
    public Integer bust;

    @ApiModelProperty(value = "臂围", name = "armSize")
    public Integer armSize;

    @ApiModelProperty(value = "臀围", name = "hipline")
    public Integer hipline;

    @ApiModelProperty(value = "腰围", name = "waistline")
    public Integer waistline;

    @ApiModelProperty(value = "大腿围度", name = "thighSize")
    public Integer thighSize;

    @ApiModelProperty(value = "小腿围度", name = "shankSize")
    public Integer shankSize;
}
