package sport.domain;

import lombok.Data;

import javax.annotation.Generated;

@Data
public class User {

    private Integer userId;

    private String mobile;

    private String nickName;

    private String avatar;

    private Boolean gender;

    private String livingCity;

    private String sportTarget;
}
