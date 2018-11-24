package sport.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sport.dto.CourseIntroduceDto;
import sport.util.ResultUtil;
import sport.vo.ResultVo;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mycourses")
@Api("我的课程apis")
public class MyCourseController {

    @ApiOperation(value = "搜索课程 by 课程名称")
    @ApiImplicitParam(value = "课程名称", name = "courseName", required = true,
    dataType = "int")
    @GetMapping("/findCourseByname")
    public ResultVo<List<CourseIntroduceDto>> getCourseByName(
            @RequestParam("courseName") String courseName) {
        //搜索范围仅限已经结束的直播课
        List<CourseIntroduceDto> courseIntroduceDtoList = new ArrayList<>();

        //屏蔽掉开始时间和结束时间

        //课程类型没有商定
        CourseIntroduceDto courseIntroduceDto = new CourseIntroduceDto();
        courseIntroduceDto.setTitle("那些年一起跳过的拉丁舞");
        courseIntroduceDto.setPublisher("拉丁舞大叔");
        courseIntroduceDto.setType(0);
        courseIntroduceDtoList.add(courseIntroduceDto);

        return ResultUtil.success(courseIntroduceDtoList);
    }

    @ApiOperation(value = "搜索课程 by 主播")
    @ApiImplicitParam(value = "主播名称", name = "userName")
    @GetMapping("/findCourseByPublisher")
    public ResultVo<List<CourseIntroduceDto>> findClassByUserName(@RequestParam("userName") String userName) {
        //搜索范围仅限已经结束的直播课,普通课程暂时不假如我的课程

        return ResultUtil.success(null);
    }

    @ApiOperation(value = "获取我的课程列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始位置", name = "start"),
            @ApiImplicitParam(value = "拉取的课程数量", name = "offset")
    })
    @GetMapping("/list")
    public ResultVo<List<CourseIntroduceDto>> getMyCourses(@RequestParam("start") int start,
                                                           @RequestParam(name = "offset", defaultValue = "10") int offset) {
        return ResultUtil.success(null);
    }
}
