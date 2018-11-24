package sport.controller.course;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sport.dto.LiveCourseDto;
import sport.util.ResultUtil;
import sport.vo.LiveCourseRecordVo;
import sport.vo.LiveCourseVo;
import sport.vo.ResultVo;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/liveCourses")
@Api(value = "约吧课程apis")
public class LiveCourseController {

    @ApiOperation(value = "创建约吧课程")
    @PutMapping("/course")
    public ResultVo<String> createCourse(@Validated LiveCourseVo courseVo, BindingResult result) {
        if (result.hasErrors()) {
//            String message = result.getFieldError().getDefaultMessage();
            List<FieldError> fieldErrors = result.getFieldErrors();

            StringBuilder stringBuilder = new StringBuilder();
            for (FieldError error :
                    fieldErrors) {
                stringBuilder.append(error.getDefaultMessage()).append(",");
            }
            return ResultUtil.error(stringBuilder.toString());
        }

        //记得存publishName

        //直播课程开课不可以超过1小时

        //只能创建3天以为的直播课程

        //每个课程只能添加12个伙伴(数量还是要根据时间来定)

        //一个发布者不能发布两个时间有交集的直播课
        //即发布的时候，查看发布者那个时间段是否有要参加的课程，假如有，发布失败

        //每天最多可开3个直播课

        //todo 伙伴从互相关注的用户里面取

        //发布人和伙伴分开存

        //todo 存储课程数据，要看缓存架构

        //创建课程id，然后存到运动记录,课程id暂定userid+时间戳

        return ResultUtil.success("课程创建成功");
    }

    @ApiOperation(value = "获取未结束的直播课程")
    @GetMapping("/course/{courseId}")
    public ResultVo<LiveCourseDto> getCourse(@PathParam("courseId") String courseId) {
        //todo 根据课程id获取课程信息

        //最好在客户端做缓存，防止重复拉取

        //发布人和伙伴不在一个表里，要获取所有人的id信息需要注意

        LiveCourseDto liveCourseDto = new LiveCourseDto();
        liveCourseDto.setStartTime(123);
        return ResultUtil.success(liveCourseDto);
    }

    @ApiOperation(value = "创建课程记录", notes = "目前策略：完成一个动作一条记录")
    @PutMapping("/course/record")
    public ResultVo productRecord(@Validated LiveCourseRecordVo recordVo) {
        //获取第一条运动记录，会在运动记录表里创建一个以课程名为主题的记录，
        //创建主题时间为运动开始时间

        //同个用户同个课程每上传一个记录更新这个主题的结束时间

        //运动记录需要通过通知,同步到其他上课成员进行显示

        //同步到运动时间表中

        return ResultUtil.success(null);
    }

    //获取课程记录，在我的课程里面，这里不做
}
