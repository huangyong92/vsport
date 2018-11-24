package sport.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "体重数据")
public class Weight {

    public Integer userId;

    @ApiModelProperty(value = "体重", name = "weight", required = true, dataType = "float")
    public Float weight;

    @ApiModelProperty(value = "记录体重时间", name = "recordTime", required = true, dataType = "long")
    public Long recordTime;
}
