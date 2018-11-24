package sport.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sport.enums.ResultEnum;
import sport.service.SmsService;
import sport.util.EnumUtil;
import sport.util.FormatVerifyUtil;
import sport.util.ResultUtil;
import sport.vo.ResultVo;

@RestController
@RequestMapping("/sms")
@Api(value = "验证码逻辑")
public class SmsController {

    @Autowired
    private SmsService mSmsService;

    @ApiOperation(value = "发送短信API", notes = "短信类型 0:登录/注册 1:找回密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户手机号", dataType = "string",
                    required = true),
            @ApiImplicitParam(name = "smsType", value = "短信类型", dataType = "string",
                    required = true, example = "0")
    })
    @GetMapping("/code")
    public ResultVo getCode(@RequestParam("phone") String phone,
                                @RequestParam("smsType") String smsType) {

//        ResultVo resultVo = new ResultVo();
        boolean isDayLimit = mSmsService.isDayLimit(phone);

        if (isDayLimit) {
//            resultVo.setCode(ResultEnum.SMS_DAY_LIMIT.getCode());
//            resultVo.setMsg(ResultEnum.SMS_DAY_LIMIT.getMsg());

            return ResultUtil.error(ResultEnum.SMS_DAY_LIMIT.getMsg());
        }

        boolean isInterval = mSmsService.isIntervalOk(phone);
        if (!isInterval) {
//            resultVo.setCode(ResultEnum.SMS_INVAILD_CODE.getCode());
//            resultVo.setMsg(ResultEnum.SMS_INVAILD_CODE.getMsg());

            return ResultUtil.error(ResultEnum.SMS_INVAILD_CODE.getMsg());
        }

        int statuCode = mSmsService.sendSmsCode(phone, smsType);
//        resultVo.setCode(statuCode);
        if (statuCode != 0) {
            return ResultUtil.error(EnumUtil.getEnum(ResultEnum.class, statuCode).getMsg());
//            resultVo.setMsg(EnumUtil.getEnum(ResultEnum.class, statuCode).getMsg());
        }

//        resultVo.setMsg("发送成功");
        return ResultUtil.success(null);
    }

    @ApiOperation(value = "验证短信API", notes = "验证码为4位")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户手机号", dataType = "string",
                    required = true),
            @ApiImplicitParam(name = "code", value = "验证码", dataType = "string",
                    required = true, example = "9876")
    })
    @PostMapping("/code")
    public ResultVo verifyCode(@RequestParam("phone") String phone,
                               @RequestParam("code") String code) {
//        ResultVo resultVo = new ResultVo();
        Boolean isPhone = FormatVerifyUtil.isPhone(phone);

        if (!isPhone) {
//            resultVo.setCode(ResultEnum.SMS_MOBILE_ERROR.getCode());
//            resultVo.setMsg(ResultEnum.SMS_MOBILE_ERROR.getMsg());

            return ResultUtil.error(ResultEnum.SMS_MOBILE_ERROR.getMsg());
        }

        int statuCode = mSmsService.verifySmsCode(phone, code);
        if (statuCode != 0) {
//            resultVo.setCode(statuCode);
//            resultVo.setMsg(EnumUtil.getEnum(ResultEnum.class, statuCode).getMsg());

            return ResultUtil.error(EnumUtil.getEnum(ResultEnum.class, statuCode).getMsg());
        }

//        resultVo.setCode(0);
//        resultVo.setMsg("验证成功");

        return ResultUtil.success(null);
    }
}
