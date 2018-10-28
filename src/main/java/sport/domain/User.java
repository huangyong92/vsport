package sport.domain;

import lombok.Data;

@Data
public class User {

    private Integer userId;

    private String phone;

    private String password;

    private String nickName;

    private String avatar;

    private Integer gender;

    private String livingCity;

    private String sportTarget;
}
