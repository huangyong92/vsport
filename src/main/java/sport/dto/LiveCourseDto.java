package sport.dto;

import lombok.Data;
import sport.domain.Exercise;

import java.util.List;

@Data
public class LiveCourseDto {

    public long startTime;

    public long endTime;

    public List<UserSimpleDto> partnerIds;

    public List<Exercise> actions;
}
