package sport.domain.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sport.domain.SportDetail;
import sport.domain.SportTheme;
import sport.domain.SportTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserSportRepostoryTest {

    @Autowired
    private UserSportRepostory userSportRepostory;

//    @Test
//    public void batchCreateTheme() {
//        //课程的主题
//        SportTheme sportTheme = new SportTheme();
//        sportTheme.setCourseId("32018112509");
//        sportTheme.setStartTime(2018112300l);
//        sportTheme.setEndTime(20181125l);
//        sportTheme.setTheme("露营直播");
//        sportTheme.setUserId(1);
//
//        //手动创建的主题
//        SportTheme sportTheme1 = new SportTheme();
//        sportTheme1.setStartTime(20181123l);
//        sportTheme1.setEndTime(20181125l);
//        sportTheme1.setTheme("一起去划船吧");
//        sportTheme1.setUserId(1);
//
//        List<SportTheme> sportThemeList = new ArrayList<>();
//        sportThemeList.add(sportTheme);
//        sportThemeList.add(sportTheme1);
//
//        userSportRepostory.batchCreateTheme(sportThemeList);
//    }
//
//    @Test
//    public void batchCreateSportDetail() {
//        SportDetail sportDetail1 = new SportDetail();
//        sportDetail1.setSportType(0);
//        sportDetail1.setSportName("坦克大战");
//        sportDetail1.setSportContent("开着坦克杀手");
//        sportDetail1.setExercisePart("手臂");
//        sportDetail1.setSportStatu("非常爽");
//        sportDetail1.setSportThemeId(1);
//
//        SportDetail sportDetail2 = new SportDetail();
//        sportDetail2.setSportType(0);
//        sportDetail2.setSportName("轰击敌军");
//        sportDetail2.setSportContent("开着飞鹰战机");
//        sportDetail2.setExercisePart("手臂");
//        sportDetail2.setSportStatu("很快");
//        sportDetail2.setSportThemeId(1);
//
//        List<SportDetail> sportDetailList = new ArrayList<>();
//        sportDetailList.add(sportDetail1);
//        sportDetailList.add(sportDetail2);
//
//        userSportRepostory.batchCreateSportDetail(sportDetailList);
//    }
//
//    @Test
//    public void batchAddSportTime() {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//        SportTime sportTime = new SportTime();
//        sportTime.setSportDate(dateFormat.format(new Date()));
//        sportTime.setUserId(1);
//        sportTime.setLastTime(30);
//
//        SportTime sportTime1 = new SportTime();
//        sportTime1.setSportDate(dateFormat.format(new Date()));
//        sportTime1.setUserId(2);
//        sportTime1.setLastTime(50);
//
//        List<SportTime> sportTimeList = new ArrayList<>();
//        sportTimeList.add(sportTime);
//        sportTimeList.add(sportTime1);
//
//        userSportRepostory.batchAddSportTime(sportTimeList);
//    }

    @Test
    public void updateSportTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        userSportRepostory.updateSportTime(2,
                dateFormat.format(new Date()),
                100);
    }

    @Test
    public void getSportThemeList() {
        List<SportTheme> sportThemeList = userSportRepostory.getSportThemeList(1, 0, 5);

        assertNotEquals(0, sportThemeList.size());
    }

    @Test
    public void getSportDetailSById() {
        List<SportDetail> sportDetailList = userSportRepostory.getSportDetailSById(Arrays.asList(3, 4));

        assertNotEquals(0, sportDetailList.size());
    }

    @Test
    public void getSportDetailSByTheme() {
        List<SportDetail> sportDetailList = userSportRepostory.getSportDetailSByTheme(1);

        assertNotEquals(0, sportDetailList.size());
    }

    @Test
    public void getSportTimeList() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 10, 23);
        Date date1 = calendar.getTime();
        calendar.set(2018, 10, 25);
        Date date2 = calendar.getTime();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<SportTime> sportTimeList = userSportRepostory.getSportTimeList(1, dateFormat.format(date1), dateFormat.format(date2), 0, 5);

        assertNotEquals(0, sportTimeList.size());
    }
}