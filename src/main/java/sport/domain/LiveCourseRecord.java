package sport.domain;

import lombok.Data;

@Data
public class LiveCourseRecord {

    public Integer id;

    public String liveCourseId;

    public Integer userId;

    public Integer contentId;

    public Long finishTime;

    public Integer lastTime;

    public String actionName;
}
