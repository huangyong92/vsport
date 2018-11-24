package sport.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "动作大全")
public class ExerciseGuide {

    @ApiModelProperty(value = "动作名称", name = "actionName", required = true)
    @NotBlank(message = "请填写动作名称")
    public String actionName;

    @ApiModelProperty(value = "锻炼部位", name = "exercisePart", required = true)
    @NotBlank(message = "请填写锻炼部位")
    public String exercisePart;

    @ApiModelProperty(value = "动作描述", name = "actionDescribe", required = true)
    @NotBlank(message = "请描述动作")
    public String actionDescribe;
}
