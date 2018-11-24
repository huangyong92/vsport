package sport.controller.account;

import io.swagger.annotations.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sport.domain.User;
import sport.dto.RegisterDto;
import sport.enums.LoginEnum;
import sport.enums.ResultEnum;
import sport.service.*;
import sport.util.EnumUtil;
import sport.util.FormatVerifyUtil;
import sport.util.ResultUtil;
import sport.vo.RegiestVo;
import sport.vo.ResultVo;

@RestController
@RequestMapping("/account")
@Api(value = "账号Apis")
public class AccountController {

    @Autowired
    private UserService mUserService;

    @Autowired
    private DeviceService mDeviceService;

    @Autowired
    private TokenService mTokenService;

    @Autowired
    private SmsService mSmsService;

    @Autowired
    private PushService mPushService;

    @ApiOperation(value = "注册用户", notes = "注册成功即登录成功")
    @PostMapping("/profile")
    public ResultVo register(@Validated RegiestVo regiestVo,
                            BindingResult result) {
        if (result.hasErrors()) {
            FieldError fieldErrors = result.getFieldError();
            return ResultUtil.error(fieldErrors.getDefaultMessage());
        }

        //todo 验证手机号可以放到拦截器或则是过滤器里
        //验证手机号码
        boolean isPhone = FormatVerifyUtil.isPhone(regiestVo.phone);
        if (!isPhone) {
            return ResultUtil.error(ResultEnum.SMS_MOBILE_ERROR.getMsg());
        }

        //确保手机号没有注册过
        User isExitUser = mUserService.findUserByPhone(regiestVo.phone);

        if (isExitUser != null) {
            return ResultUtil.error(ResultEnum.PHONE_IS_EXIT.getMsg());
        }

        //保存用户基本信息
        User user = new User();
        BeanUtils.copyProperties(regiestVo, user);
        String userId = String.valueOf(mUserService.addUser(user));

        //保存AppId
        mDeviceService.updateDeviceStatu(userId, regiestVo.appId, LoginEnum.LOGIN.getStatu());
        //分发token
        String token = mTokenService.createToken(userId);

        RegisterDto dto = new RegisterDto();
        dto.setToken(token);
        dto.setUserId(userId);

        return ResultUtil.success(dto);
    }

    @ApiOperation(value = "手机号登录", notes = "设备上没有用户信息时，用手机号登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "推送对应的clientId",
                    dataType = "string", required = true),
            @ApiImplicitParam(name = "phone", value = "手机号",
                    dataType = "string", required = true),
            @ApiImplicitParam(name = "smsCode", value = "验证码",
                    dataType = "string", required = true)
    })
    @PostMapping("/login/phone")
    public ResultVo loginByPhone(@RequestParam("appId") String appId,
                                  @RequestParam("phone") String phone,
                                  @RequestParam("smsCode") String smsCode) {
        //验证手机格式 todo
        boolean isPhone = FormatVerifyUtil.isPhone(phone);
        if (!isPhone) {
            return ResultUtil.error(ResultEnum.SMS_MOBILE_ERROR.getMsg());
        }
        //验证码是否正常
        int code = mSmsService.verifySmsCode(phone, smsCode);
        if (code != 0) {
            return ResultUtil.error(EnumUtil.getEnum(ResultEnum.class, code).getMsg());
        }

        //获取uid
        User user = mUserService.findUserByPhone(phone);
        String userId = String.valueOf(user.getUserId());

        RegisterDto data = login(userId, appId);

        return ResultUtil.success(data);
    }

    @ApiOperation(value = "密码登录", notes = "设备上有用户信息时，用密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appId", value = "推送对应的clientId",
                    dataType = "string", required = true),
            @ApiImplicitParam(name = "nikeName", value = "用户名",
                    dataType = "string", required = true),
            @ApiImplicitParam(name = "password", value = "密码",
                    dataType = "string", required = true)
    })
    @PostMapping("/login/password")
    public ResultVo loginByPassword(@RequestParam("appId") String appId,
                                    @RequestParam("phone") String phone,
                                    @RequestParam("password") String password) {
        boolean isPhone = FormatVerifyUtil.isPhone(phone);
        if (!isPhone) {
            return ResultUtil.error(ResultEnum.SMS_MOBILE_ERROR.getMsg());
        }

        //验证身份
        String userId = mUserService.getUserIdByPassword(phone, password);
        if (userId == null) {
            return ResultUtil.error(ResultEnum.PASSWORD_LOGIN_ERROR.getMsg());
        }

        RegisterDto data = login(userId, appId);

        return ResultUtil.success(data);
    }

    @ApiOperation(value = "退出登录", notes = "头信息需要带authorization:Bearer:token")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户Id",
                    dataType = "string", required = true),
            @ApiImplicitParam(name = "appId", value = "推送对应的clientId",
                    dataType = "string", required = true)
    })
    @PatchMapping("/loginOut")
    public ResultVo loginOut(@RequestHeader(value = "Authorization") String authorization,
                            @RequestParam("userId") String userId,
                             @RequestParam("appId") String appId) {
        //验证token
        String[] tokenInfo = authorization.split(":");
        String token = null;
        if ("Bearer".equals(tokenInfo[0])) {
            token = tokenInfo[1];
        } else {
            return ResultUtil.error("token 格式正确");
        }

        //验证uid
        String uid = mTokenService.parseToken(token);
        if (!uid.equals(userId)) {
            return ResultUtil.error("userId不正确");
        }

        //更改appid状态
        mDeviceService.updateDeviceStatu(userId, appId, LoginEnum.LOGOUT.getStatu());
        //将token列入黑名单
        mTokenService.addToBlackList(token);

        return ResultUtil.success(null);
    }

    private RegisterDto login(String userId, String appId) {
        RegisterDto registerDTO = new RegisterDto();

        //检查是否有别的地方登录
        String currentDevice = mDeviceService.getCurrentDevice(userId);
        if (currentDevice != null) {
            mPushService.notifLoginOut(currentDevice);
        }
        //保存appid,并改变状态
        mDeviceService.updateDeviceStatu(userId, appId, LoginEnum.LOGIN.getStatu());
        //分发token
        String token = mTokenService.createToken(userId);
        registerDTO.setUserId(userId);
        registerDTO.setToken(token);

        return registerDTO;
    }
}
