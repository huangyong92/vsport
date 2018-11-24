package sport.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import sport.dto.UserSimpleDto;
import sport.util.ResultUtil;
import sport.vo.ResultVo;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/relation")
@Api(value = "用户关系apis")
public class RelationController {

    @ApiOperation(value = "添加关注/或则取消关注")
    @ApiImplicitParam(value = "我的Id", name = "myUserId", required = true, dataType = "int")
    @PatchMapping("/attention/{userId}")
    public ResultVo toAttention(@PathParam("userId") int userId,
                                @RequestParam("myUserId") int myUserId) {
        //粉丝列表可关注粉丝，也可取消关注

        //判断是否关注，如果关注就就取消关注，不然就添加关注

        return ResultUtil.success(null);
    }

    @ApiOperation(value = "获取关注列表")
    @ApiImplicitParam(value = "用户标识", name = "userId", required = true, dataType = "int")
    @GetMapping("/attentions")
    public ResultVo<List<UserSimpleDto>> getAttentions(
            @RequestParam("userId") int userId) {

        //直接返回关注列表,不需要看对方是否关注我

        List<UserSimpleDto> userSimpleDtoList = new ArrayList<>();

        UserSimpleDto userSimpleDto = new UserSimpleDto();
        userSimpleDto.setName("奇迹");
        userSimpleDto.setUserId(1);
        userSimpleDtoList.add(userSimpleDto);

        return ResultUtil.success(userSimpleDtoList);
    }

    @ApiOperation(value = "获取粉丝列表", notes = "isFollewer代表我是否关注他")
    @ApiImplicitParam(value = "用户标识", name = "userId", required = true, dataType = "int")
    @GetMapping("/followers")
    public ResultVo<List<UserSimpleDto>> getFollewers(
            @RequestParam("userId") int userId) {

        //获取我是否关注别人，并返回

        List<UserSimpleDto> userSimpleDtoList = new ArrayList<>();

        UserSimpleDto userSimpleDto = new UserSimpleDto();
        userSimpleDto.setName("成都小火锅");
        userSimpleDto.setUserId(100);
        userSimpleDto.setFollower(true);
        userSimpleDtoList.add(userSimpleDto);

        return ResultUtil.success(userSimpleDtoList);
    }

    @ApiOperation(value = "搜索用户")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "用户手机号", name = "phone", required = true, dataType = "String"),
            @ApiImplicitParam(value = "用户标识", name = "userId", required = true, dataType = "int")
    })
    @GetMapping("/findUser")
    public ResultVo<UserSimpleDto> getUser(@RequestParam("phone") String phone,
                                           @RequestParam("userId") int userId) {
        UserSimpleDto userSimpleDto = new UserSimpleDto();
        userSimpleDto.setName("成都小火锅");
        userSimpleDto.setUserId(100);
        userSimpleDto.setFollower(true);

        return ResultUtil.success(userSimpleDto);
    }
}
