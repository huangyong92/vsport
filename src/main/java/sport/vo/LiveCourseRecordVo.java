package sport.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "直播课完成的动作内容")
public class LiveCourseRecordVo {

    @ApiModelProperty(value = "课程Id", name = "courseId", required = true)
    @NotBlank(message = "请上传课程Id")
    public String courseId;

    @ApiModelProperty(value = "是谁完成动作", name = "userId", required = true)
    @NotBlank(message = "请告诉我是谁完成了动作")
    public String userId;

    @ApiModelProperty(value = "完成的动作内容", name = "actionName", required = true)
    @NotBlank(message = "请告诉我完成了什么动作")
    public String actionName;

}
