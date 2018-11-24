package sport.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import sport.domain.Exercise;
import sport.dto.SportThemeDto;
import sport.dto.SportTimeDto;
import sport.util.ResultUtil;
import sport.vo.ResultVo;
import sport.vo.SportRecordVo;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sportRecords")
@Api(value = "运动轨迹apis")
public class SportController {

    @ApiOperation(value = "获取运动频率列表", notes = "时间级别同体型数据")
    @ApiImplicitParam(value = "timeGrade", name = "时间级别", dataType = "int")
    @GetMapping("/times/{userId}")
    public ResultVo<List<SportTimeDto>> getSportTime(
            @PathParam("userId") int userId,
            @RequestParam(value = "timeGrade", required = false) int timeGrade) {
        //假如不传时间级别，按照能够获取到数据的那个时间级别来选择

        List<SportTimeDto> sportTimeDtoList = new ArrayList<>();
        SportTimeDto sportTimeDto = new SportTimeDto();
        sportTimeDto.setSportDate("2018-11-19");
        sportTimeDto.setLastTime(100);
        //持续时间到底是秒还是字符串，可以根据前端来沟通

        sportTimeDtoList.add(sportTimeDto);

        return ResultUtil.success(sportTimeDtoList);
    }

    @ApiOperation(value = "获取用户主题列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "开始位置", name = "start", required = true, dataType = "int"),
            @ApiImplicitParam(value = "需要条数", name = "offset", required = false, dataType = "int")
    })
    @GetMapping("/themes")
    public ResultVo<List<SportThemeDto>> getSportThemes(
            @RequestParam("start") int start,
            @RequestParam(value = "offset", required = false, defaultValue = "10") int offset) {

        //主题按照时间排序

        //数据库里是startTime 和endTime,客户端如果需要持续时间，就换算
        List<SportThemeDto> sportThemeDtoList = new ArrayList<>();

        SportThemeDto sportThemeDto = new SportThemeDto();
        sportThemeDto.setTheme("与小丫头一起上的拉伸课");
        sportThemeDto.setStartTime("2017-11-19 15:00");
        sportThemeDto.setLastTime("1小时");
        sportThemeDtoList.add(sportThemeDto);

        return ResultUtil.success(sportThemeDtoList);
    }

    @ApiOperation(value = "获取运动详情")
    @ApiImplicitParam(value = "主题标识", name = "themeId", required = true, dataType = "int")
    @GetMapping("/detail/{themeId}")
    public ResultVo<List<Exercise>> getSportDetail(@PathParam("themeId") int themeId) {

        List<Exercise> exerciseList = new ArrayList<>();
        Exercise exercise = new Exercise();
        exercise.setName("引体向上");
        exercise.setContent("10x12;15x10;");
        exercise.setType(0);
        exerciseList.add(exercise);

        return ResultUtil.success(exerciseList);
    }

    @ApiOperation(value = "设置运动记录")
    @PutMapping("/record")
    public ResultVo setSportRecord(@RequestParam("userId") int userId,
                                   SportRecordVo sportRecordVo) {
        //普通课程以后可以加入运动记录，现在不行

        //运动记录的结束时间为当前提交时间

        return ResultUtil.success(null);
    }
}
