package sport.domain;

import lombok.Data;

@Data
public class LiveCourseIntroduce {

    public String id;

    public Integer publisherId;

    public Long startTime;

    public Long endTime;

    public String publisherName;

    public String title;
}
