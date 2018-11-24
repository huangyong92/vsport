package sport.domain.repository;

import org.apache.ibatis.annotations.Param;
import sport.domain.SportDetail;
import sport.domain.SportTheme;
import sport.domain.SportTime;

import java.util.List;

public interface UserSportRepostory {

    void batchCreateTheme(List<SportTheme> sportThemeList);

    void batchCreateSportDetail(List<SportDetail> sportDetailList);

    void batchAddSportTime(List<SportTime> sportTimeList);

    //以天为单位更新的
    void updateSportTime(@Param("userId") int userId,
                         @Param("sportDate") String sportDate,
                         @Param("lastTime") int lastTime);

    List<SportTheme> getSportThemeList(@Param("userId") int userId,
                                   @Param("pageNum") int pageNum,
                                   @Param("pageSize") int pageSize);

    List<SportDetail> getSportDetailSById(List<Integer> contentId);

    List<SportDetail> getSportDetailSByTheme(int themeId);

    List<SportTime> getSportTimeList(@Param("userId") int userId,
                                     @Param("startTime") String startTime,
                                     @Param("endTime") String endTime,
                                     @Param("pageNum") int pageNum,
                                     @Param("pageSize") int pageSize);
}
