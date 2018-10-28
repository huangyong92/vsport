package sport.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sport.domain.User;
import sport.dto.RegisterDTO;
import sport.enums.LoginEnum;
import sport.enums.ResultEnum;
import sport.service.*;
import sport.util.EnumUtil;
import sport.util.FormatVerifyUtil;
import sport.util.ResultUtil;
import sport.vo.RegiestVo;
import sport.vo.ResultVo;

@RestController
@RequestMapping("/user")
public class UserController {

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

        //保存用户基本信息
        User user = new User();
        BeanUtils.copyProperties(regiestVo, user);
        String userId = String.valueOf(mUserService.addUser(user));

        //保存AppId
        mDeviceService.updateDeviceStatu(userId, regiestVo.appId, LoginEnum.LOGIN.getStatu());
        //分发token
        String token = mTokenService.createToken(userId);

        RegisterDTO dto = new RegisterDTO();
        dto.setToken(token);
        dto.setUserId(userId);

        return ResultUtil.success("注册成功", dto);
    }

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

        RegisterDTO data = login(userId, appId);

        return ResultUtil.success("登录成功", data);
    }

    @PostMapping("/login/password")
    public ResultVo loginByPassword(@RequestParam("appId") String appId,
                                    @RequestParam("nikeName") String nikeName,
                                    @RequestParam("password") String password) {
        //验证身份
        String userId = mUserService.getUserIdByPassword(nikeName, password);
        if (userId == null) {
            return ResultUtil.error("账号验证失败");
        }

        RegisterDTO data = login(userId, appId);

        return ResultUtil.success("登录成功", data);
    }

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

        return ResultUtil.success("退出登录成功", null);
    }

    private RegisterDTO login(String userId, String appId) {
        RegisterDTO registerDTO = new RegisterDTO();

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
