package sport.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import sport.domain.Exercise;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(value = "直播课程内容")
public class LiveCourseVo {

    @ApiModelProperty(value = "创建人Id", name = "publisher", required = true)
    @NotBlank(message = "请上传创建人id")
    public String publisher;

    @ApiModelProperty(value = "标题", name = "title", required = true)
    @NotBlank(message = "请上传标题")
    public String title;

    @ApiModelProperty(value = "上课时间", name = "startTime", required = true)
    @NotNull(message = "请上传上课时间")
    public Long startTime;

    @ApiModelProperty(value = "下课时间", name = "endTime", required = true)
    @NotNull(message = "请上传下课时间")
    public Long endTime;

    @ApiModelProperty(value = "伙伴Id列表", name = "partnerIds", required = true)
    @NotEmpty(message = "请上传伙伴列表")
    public List<String> partnerIds;

    @ApiModelProperty(value = "动作列表", name = "actions", required = true)
    @NotEmpty(message = "请上传动作列表")
    public List<Exercise> actions;
}
