package sport.dto;

import lombok.Data;

@Data
public class UserSimpleDto {

    public int userId;

    public String name;

    public String avatar;

    public boolean isFollower;
}
