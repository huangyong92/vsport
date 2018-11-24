package sport.dto;

import lombok.Data;

@Data
public class UserImportantInfoDto {

    public String name;

    public String avatar;

    public int followerCount;

    public int attentionCount;

    public String weight;

    public String BMI;

    public int sportTime;
}
