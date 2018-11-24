package sport.controller.course;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import sport.dto.CourseIntroduceDto;
import sport.util.ResultUtil;
import sport.vo.ResultVo;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/courses")
@Api(value = "课程公共Apis,搜索课程，获取课程列表")
public class CourseController {


    @ApiOperation(value = "搜索课程 by 课程名称")
    @ApiImplicitParam(value = "课程名称", name = "courseName")
    @GetMapping("/findCourseByname")
    public ResultVo<List<CourseIntroduceDto>> findClassByCourseName(@RequestParam("courseName") String courseName) {
        //todo 搜索范围 普通课程，和自己还未结束的直播课程

        return ResultUtil.success(null);
    }

    @ApiOperation(value = "搜索课程 by 主播")
    @ApiImplicitParam(value = "主播名称", name = "userName")
    @GetMapping("/findCourseByPublisher")
    public ResultVo<List<CourseIntroduceDto>> findClassByUserName(@RequestParam("userName") String userName) {
        //todo 搜索范围 普通课程，和自己还未结束的直播课程

        List<CourseIntroduceDto> courseIntroduceDtoList = new ArrayList<>();

        //课程类型没有商定
        CourseIntroduceDto courseIntroduceDto = new CourseIntroduceDto();
        courseIntroduceDto.setType(0);
        courseIntroduceDto.setPublisher("轮滑要飞");
        courseIntroduceDto.setStartTime(10000);
        courseIntroduceDto.setEndTime(2000);
        courseIntroduceDto.setTitle("去海边轮滑");
        courseIntroduceDtoList.add(courseIntroduceDto);

        return ResultUtil.success(courseIntroduceDtoList);
    }

    @ApiOperation(value = "获取课程列表, 直播课置首")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始位置", name = "start"),
            @ApiImplicitParam(value = "拉取的课程数量", name = "offset")
    })
    @GetMapping("/list")
    public ResultVo<List<CourseIntroduceDto>> getCourses(@RequestParam("start") int start,
                                                         @RequestParam(name = "offset", defaultValue = "10") int offset) {
        //todo 普通课程和直播课程具有不同的课程类型
        //todo 拉取的是未结束的直播课程
        //todo 测试数据是否按时间排序
        //直播课程置首，自己发布的直播课程更置顶

        CourseIntroduceDto courseIntroduceDto = new CourseIntroduceDto();
        courseIntroduceDto.setTitle("我是第一个课程");
        List<CourseIntroduceDto> introduceDtoList = new ArrayList<>();
        introduceDtoList.add(courseIntroduceDto);

        return ResultUtil.success(introduceDtoList);
    }

}
