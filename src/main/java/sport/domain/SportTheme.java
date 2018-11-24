package sport.domain;

import lombok.Data;

@Data
public class SportTheme {

    public Integer sportThemeId;

    public Integer userId;

    public String theme;

    public Long startTime;

    public Long endTime;

    public String courseId;


}
