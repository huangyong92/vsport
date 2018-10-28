package sport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sport.enums.ResultEnum;
import sport.service.SmsService;
import sport.util.EnumUtil;
import sport.util.FormatVerifyUtil;
import sport.vo.ResultVo;

@RestController
@RequestMapping("/sms")
public class SmsController {

    @Autowired
    private SmsService mSmsService;

    @GetMapping("/code")
    public ResultVo getCode(@RequestParam("phone") String phone,
                                @RequestParam("smsType") String smsType) {

        ResultVo resultVo = new ResultVo();
        boolean isDayLimit = mSmsService.isDayLimit(phone);

        if (isDayLimit) {
            resultVo.setCode(ResultEnum.SMS_DAY_LIMIT.getCode());
            resultVo.setMsg(ResultEnum.SMS_DAY_LIMIT.getMsg());

            return resultVo;
        }

        boolean isInterval = mSmsService.isIntervalOk(phone);
        if (!isInterval) {
            resultVo.setCode(ResultEnum.SMS_INVAILD_CODE.getCode());
            resultVo.setMsg(ResultEnum.SMS_INVAILD_CODE.getMsg());

            return resultVo;
        }

        int statuCode = mSmsService.sendSmsCode(phone, smsType);
        resultVo.setCode(statuCode);
        if (statuCode != 0) {
            resultVo.setMsg(EnumUtil.getEnum(ResultEnum.class, statuCode).getMsg());
        }

        resultVo.setMsg("发送成功");
        return resultVo;
    }

    @PostMapping("/code")
    public ResultVo verifyCode(@RequestParam("phone") String phone,
                               @RequestParam("code") String code) {
        ResultVo resultVo = new ResultVo();
        Boolean isPhone = FormatVerifyUtil.isPhone(phone);

        if (!isPhone) {
            resultVo.setCode(ResultEnum.SMS_MOBILE_ERROR.getCode());
            resultVo.setMsg(ResultEnum.SMS_MOBILE_ERROR.getMsg());

            return resultVo;
        }

        int statuCode = mSmsService.verifySmsCode(phone, code);
        if (statuCode != 0) {
            resultVo.setCode(statuCode);
            resultVo.setMsg(EnumUtil.getEnum(ResultEnum.class, statuCode).getMsg());

            return resultVo;
        }

        resultVo.setCode(0);
        resultVo.setMsg("验证成功");

        return resultVo;
    }
}
