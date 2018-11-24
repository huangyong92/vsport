package sport.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import sport.domain.ExerciseGuide;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@ApiModel(value = "示范动作的课程")
public class GuideCourseVo {

    @ApiModelProperty(value = "课程名称", name = "courseTitle", required = true)
    @NotBlank(message = "请填课程名称")
    public String courseTitle;

    @ApiModelProperty(value = "课程描述", name = "courseDescribe")
    public String courseDescribe;

    @ApiModelProperty(value = "课程内容", name = "courseContent", required = true)
    @NotEmpty(message = "请填写课程内容")
    public List<ExerciseGuide> courseContent;
}
