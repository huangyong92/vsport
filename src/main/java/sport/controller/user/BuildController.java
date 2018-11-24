package sport.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import sport.domain.BodilyForm;
import sport.domain.Weight;
import sport.dto.BodilyFormDto;
import sport.util.ResultUtil;
import sport.vo.ResultVo;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userBuild")
@Api(value = "用户体型apis")
public class BuildController {

    @ApiOperation(value = "获取用户体型记录", notes = "时间级别: 1:一天 2:一周 3:一月 4:一季度 5:半年 6:所有")
    @ApiImplicitParam(value = "timeGrade", name = "时间级别", dataType = "int")
    @GetMapping("/{userId}")
    public ResultVo<BodilyFormDto> getUserBuild(@PathParam("userId") int userId,
                                                @RequestParam(value = "timeGrade", required = false) int timeGrade) {
        //以body size为主，来选择时间级别，假如获取到数据就定那个时间级别

        //体重信息以上面的时间级别为准

        BodilyFormDto bodilyFormDto = new BodilyFormDto();
        List<Weight> weightList = new ArrayList<>();
        Weight weight = new Weight();
        weight.setWeight(70f);
        bodilyFormDto.setWeights(weightList);

        List<BodilyForm> bodilyFormList = new ArrayList<>();
        bodilyFormDto.setBodilyForms(bodilyFormList);

        return ResultUtil.success(bodilyFormDto);
    }

    @ApiOperation(value = "设置体重")
    @PutMapping("/weight/{userId}")
    public ResultVo setWeight(@PathParam("userId") int userId,
                              Weight weight) {
        //体重是无符号的小数

        //体重范围要做限定

        return ResultUtil.success(null);
    }

    @ApiOperation(value = "设置身体围度")
    @PutMapping("/size/{userId}")
    public ResultVo setSize(@PathParam("userId") int userId,
                            BodilyForm bodilyForm) {
        //各个尺寸范围限制

        return ResultUtil.success(null);
    }

}
