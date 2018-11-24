package sport.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import sport.domain.Exercise;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ApiModel(value = "运动数据")
public class SportRecordVo {

    @ApiModelProperty(value = "运动主题", name = "theme", required = true)
    @NotBlank(message = "请上传运动主题")
    public String theme;

    @ApiModelProperty(value = "开始时间", name = "startTime", required = true)
    @NotBlank(message = "请上传开始运动时间")
    public String startTime;

    @ApiModelProperty(value = "结束时间", name = "endTime", required = true)
    @NotBlank(message = "请上传结束运动时间")
    public String endtTime;

    @ApiModelProperty(value = "动作列表", name = "exerciseList", required = true)
    @NotEmpty(message = "请上传动作列表")
    public List<Exercise> exerciseList;
}
