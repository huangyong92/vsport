package sport.domain.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sport.domain.LiveCourseIntroduce;
import sport.domain.LiveCourseRecord;
import sport.domain.SportDetail;
import sport.util.KeyUtil;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LiveCourseRepostoryTest {

    @Autowired
    private LiveCourseRepostory liveCourseRepostory;

    @Test
    public void findCourseListByPublisher() {
        List<LiveCourseIntroduce> courseIntroduceList =
                liveCourseRepostory.findCourseListByPublisher(3);

        Assert.assertEquals(2, courseIntroduceList.size());
    }

    @Test
    public void getMyLiveCourseIds() {
        List<String> userIds = liveCourseRepostory.getMyLiveCourseIds(1);

        Assert.assertEquals(2, userIds.size());
    }

    @Test
    public void getUserCourseList() {
        List<LiveCourseIntroduce> courseIntroduceList =
                liveCourseRepostory.getUserCourseList(
                        Arrays.asList("32018112509", "32018112515"));

        Assert.assertEquals(2, courseIntroduceList.size());
    }

    @Test
    public void getCourseIntroduce() {
        LiveCourseIntroduce courseIntroduce =
                liveCourseRepostory.getCourseIntroduce("32018112509");

        System.out.println(courseIntroduce.title);
    }

    @Test
    public void getAllParticButPublisher() {
        List<Integer> userIds =
                liveCourseRepostory.getAllParticButPublisher("32018112509");

        assertEquals(2, userIds.size());
    }

    @Test
    public void getSportDetailList() {
        List<SportDetail> sportDetailList =
                liveCourseRepostory.getSportDetailList("32018112509");

        System.out.println("detail: " + sportDetailList.get(0).getSportName() + ","
                + sportDetailList.get(1).getSportName());

        assertEquals(2, sportDetailList.size());
    }

    @Test
    public void getCourseRecord() {
        List<LiveCourseRecord> courseRecordList =
                liveCourseRepostory.getCourseRecord("32018112509");

        assertEquals(2, courseRecordList.size());
    }

    @Test
    public void batchAddCourseIntroduce() {
        LiveCourseIntroduce introduce = new LiveCourseIntroduce();
        introduce.setId(KeyUtil.getLiveCourseKey(3, 2018112509));
        introduce.setPublisherId(3);
        introduce.setPublisherName("Daniel");
        introduce.setStartTime(18112509l);
        introduce.setEndTime(181201l);
        introduce.setTitle("露营直播");

        LiveCourseIntroduce introduce1 = new LiveCourseIntroduce();
        introduce1.setId(KeyUtil.getLiveCourseKey(3, 2018112515));
        introduce1.setPublisherId(3);
        introduce1.setPublisherName("Daniel");
        introduce1.setStartTime(18112515l);
        introduce1.setEndTime(181201l);
        introduce1.setTitle("抓鱼技巧");

        List<LiveCourseIntroduce> courseIntroduceList = new ArrayList<>();
        courseIntroduceList.add(introduce);
        courseIntroduceList.add(introduce1);

        liveCourseRepostory.batchAddCourseIntroduce(courseIntroduceList);
    }

    @Test
    public void batchAddSportDetail() {
        SportDetail sportDetail = new SportDetail();
        sportDetail.setCourseId("32018112509");
        sportDetail.setSportType(0);
        sportDetail.setSportName("打豆豆");
        sportDetail.setSportContent("打豆豆200下");
        sportDetail.setRestTime(100);

        SportDetail sportDetail1 = new SportDetail();
        sportDetail1.setCourseId("32018112509");
        sportDetail1.setSportType(0);
        sportDetail1.setSportName("吃饭");
        sportDetail1.setSportContent("吃海鲜");
        sportDetail1.setRestTime(300);

        List<SportDetail> sportDetails = new ArrayList<>();
        sportDetails.add(sportDetail);
        sportDetails.add(sportDetail1);

        liveCourseRepostory.batchAddSportDetail(sportDetails);
    }

    @Test
    public void batchAddCourseToUser() {
        liveCourseRepostory.batchAddCourseToUser("32018112509", Arrays.asList(1,2));
        liveCourseRepostory.batchAddCourseToUser("320181125015", Arrays.asList(1,2));
    }

    @Test
    public void batchAddCourseRecord() {
        LiveCourseRecord liveCourseRecord = new LiveCourseRecord();
        liveCourseRecord.setLiveCourseId("32018112509");
        liveCourseRecord.setUserId(1);
        liveCourseRecord.setContentId(1);
        liveCourseRecord.setActionName("打豆豆");
        liveCourseRecord.setLastTime(1000);
        liveCourseRecord.setFinishTime(180918l);

        LiveCourseRecord liveCourseRecord1 = new LiveCourseRecord();
        liveCourseRecord1.setLiveCourseId("32018112509");
        liveCourseRecord1.setUserId(1);
        liveCourseRecord1.setContentId(2);
        liveCourseRecord1.setActionName("吃饭");
        liveCourseRecord1.setLastTime(1000);
        liveCourseRecord1.setFinishTime(180919l);

        List<LiveCourseRecord> courseRecordList = new ArrayList<>();
        courseRecordList.add(liveCourseRecord);
        courseRecordList.add(liveCourseRecord1);

        liveCourseRepostory.batchAddCourseRecord(courseRecordList);
    }
}