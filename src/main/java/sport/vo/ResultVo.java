package sport.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ResultVo<T> {

    public String msg;
    public Integer code;
    public T data;
}
