package sport.dto;

import lombok.Data;

@Data
public class UserPrimaryInfoDto {

    public String avatar;

    public String nickName;

    public String age;//todo user 表应该要添加年龄字段

    public String gender;

    public String livingCity;

    public String sportTarget;
//    public String hobby;
}
