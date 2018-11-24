package sport.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import sport.dto.UserImportantInfoDto;
import sport.dto.UserPrimaryInfoDto;
import sport.util.ResultUtil;
import sport.vo.ResultVo;
import sport.vo.UserPrimaryInfoVo;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/user")
@Api(value = "用户apis")
public class UserController {

    @ApiOperation(value = "获取用户基本信息")
    @ApiImplicitParam(value = "用户标识", name = "userId", dataType = "int")
    @GetMapping("/{userId}")
    public ResultVo<UserPrimaryInfoDto> getUser(@PathParam("userId") int userId) {
        //根据出生年月计算用户年龄

        //城市信息下传格式，需要与前端沟通

        UserPrimaryInfoDto primaryInfoDto = new UserPrimaryInfoDto();
        primaryInfoDto.setNickName("小小");
        primaryInfoDto.setAge("1");

        return ResultUtil.success(primaryInfoDto);
    }

    @ApiOperation(value = "获取用户的重要信息")
    @ApiImplicitParam(value = "用户标识", name = "userId", dataType = "int")
    @GetMapping("/important/{userId}")
    public ResultVo<UserImportantInfoDto> getImportantInfo(@PathParam("userId") int userId) {
        //头像名称等用户基本信息，是要缓存在缓存层，设置一个稍较长的过期时间
        // 为了过滤非热点数据和更新用户数据

        //粉丝数和关注数，也是需要缓存，因为数量操作会涉及到全表扫描
        //缓存策略见缓存架构分析

        //周运动时长需要根据运动记录来计算
        UserImportantInfoDto importantInfoDto = new UserImportantInfoDto();
        importantInfoDto.setName("小乌龟");
        importantInfoDto.setFollowerCount(1000);


        return ResultUtil.success(importantInfoDto);
    }

    @ApiOperation("修改用户基本信息")
    @PatchMapping("/{userId}")
    public ResultVo modifyUserProfile(@PathParam("userId") int userId,
                                      UserPrimaryInfoVo userPrimaryInfoVo) {

        return ResultUtil.success(null);
    }

    @ApiOperation("修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "手机号", name = "phone", required = true),
            @ApiImplicitParam(value = "验证码", name = "smsCode", required = true),
            @ApiImplicitParam(value = "密码", name = "password", required = true)
    })
    @PatchMapping("/password/{userId}")
    public ResultVo modifyPassword(@PathParam("userId") int userId,
                                   @RequestParam("phone") String phone,
                                   @RequestParam("smsCode") String smsCode,
                                   @RequestParam("password") String password) {
        //判断手机号码格式是否正确
        //判断用户是否存在
        //判断手机号是否和用户绑定
        //判断验证码是否正确
        //判断密码位数是否超过指定长度

        return ResultUtil.success(null);
    }

    @ApiOperation("修改手机号码")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "手机号", name = "phone", required = true),
            @ApiImplicitParam(value = "验证码", name = "smsCode", required = true),
            @ApiImplicitParam(value = "新手机号", name = "newPhone", required = true)
    })
    @PatchMapping("/phone/{userId}")
    public ResultVo modifyPhone(@PathParam("userId") int userId,
                                   @RequestParam("phone") String phone,
                                   @RequestParam("smsCode") String smsCode,
                                   @RequestParam("newPhone") String newPhone) {
        //判断手机号码格式是否正确
        //判断用户是否存在
        //判断手机号是否和用户绑定
        //判断验证码是否正确
        //判断密码位数是否超过指定长度

        return ResultUtil.success(null);
    }
}
