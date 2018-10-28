package sport.util;

import sport.vo.ResultVo;

public class ResultUtil {

    public static <T> ResultVo<T> success(String msg, T data) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg(msg);
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
