package sport.util;

import sport.enums.ResultEnum;
import sport.vo.ResultVo;

public class ResultUtil {

    public static <T> ResultVo<T> success(T data) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg(ResultEnum.OK.getMsg());
        resultVo.setData(data);

        return resultVo;
    }

    public static ResultVo error(String msg) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(-1);
        resultVo.setMsg(msg);

        return resultVo;
    }
}
