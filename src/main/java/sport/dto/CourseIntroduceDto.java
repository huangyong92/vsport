package sport.dto;

import lombok.Data;

@Data
public class CourseIntroduceDto {

    public String courseId;

    public int type;

    public String title;

    public String describe;

    public String publisher;

    public long startTime;

    public long endTime;

}
