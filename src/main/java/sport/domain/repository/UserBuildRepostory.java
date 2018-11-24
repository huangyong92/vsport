package sport.domain.repository;

import org.apache.ibatis.annotations.Param;
import sport.domain.BodilyForm;
import sport.domain.Weight;

import java.util.List;

public interface UserBuildRepostory {

    void setWeight(List<Weight> weights);

    void setBodilyForm(List<BodilyForm> bodilyForms);

    List<Weight> getWeight(@Param("userId") int userId,
                           @Param("startTime") long startTime,
                           @Param("endTime") long endTime);

    List<BodilyForm> getBodilyForm(@Param("userId") int userId,
                            @Param("startTime") long startTime,
                            @Param("endTime") long endTime);
}
