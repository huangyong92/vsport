package sport.domain.repository;

import org.apache.ibatis.annotations.Param;
import sport.domain.LiveCourseIntroduce;
import sport.domain.LiveCourseRecord;
import sport.domain.SportDetail;

import java.util.List;

public interface LiveCourseRepostory {

    List<LiveCourseIntroduce> findCourseListByPublisher(int publisherId);

//    List<LiveCourseIntroduce> getNotEndLiveCourseList(int userId);
//
//    List<LiveCourseIntroduce> getEndLiveCourseList(int userId);

    List<String> getMyLiveCourseIds(int userId);

    //批量查询
    List<LiveCourseIntroduce> getUserCourseList(List<String> courseId);

    LiveCourseIntroduce getCourseIntroduce(String courseId);

    List<Integer> getAllParticButPublisher(String courseId);

    List<SportDetail> getSportDetailList(String courseId);

    List<LiveCourseRecord> getCourseRecord(String courseId);

    void batchAddCourseIntroduce(List<LiveCourseIntroduce> courseIntroduces);

    void batchAddSportDetail(List<SportDetail> sportDetails);

    void batchAddCourseToUser(@Param("courseId") String courseId,
                              @Param("list") List<Integer> userIds);

    void batchAddCourseRecord(List<LiveCourseRecord> courseRecords);

}
