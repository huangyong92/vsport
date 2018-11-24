package sport.domain.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sport.domain.BodilyForm;
import sport.domain.Weight;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBuildRepostoryTest {

    @Autowired
    private UserBuildRepostory userBuildRepostory;

    @Test
    public void setWeight() {
        long time = System.currentTimeMillis();

        Weight weight = new Weight();
        weight.setWeight(50f);
        weight.setUserId(1);
        weight.setRecordTime(time);

        Weight weight1 = new Weight();
        weight1.setWeight(58.9f);
        weight1.setUserId(2);
        weight1.setRecordTime(time);

        List<Weight> weightList = new ArrayList<>();
        weightList.add(weight);
        weightList.add(weight1);

        userBuildRepostory.setWeight(weightList);
    }

    @Test
    public void setBodilyForm() {
        long time = System.currentTimeMillis();

        BodilyForm bodilyForm = new BodilyForm();
        bodilyForm.setUserId(1);
        bodilyForm.setBust(60);
        bodilyForm.setArmSize(20);
        bodilyForm.setWaistline(30);
        bodilyForm.setHipline(50);
        bodilyForm.setShankSize(35);
        bodilyForm.setThighSize(20);
        bodilyForm.setRecordTime(time);

        BodilyForm bodilyForm1 = new BodilyForm();
        bodilyForm1.setUserId(2);
        bodilyForm1.setBust(40);
        bodilyForm1.setArmSize(25);
        bodilyForm1.setWaistline(30);
        bodilyForm1.setHipline(40);
        bodilyForm1.setShankSize(30);
        bodilyForm1.setThighSize(20);
        bodilyForm1.setRecordTime(time);

        List<BodilyForm> bodilyFormList = new ArrayList<>();
        bodilyFormList.add(bodilyForm);
        bodilyFormList.add(bodilyForm1);

        userBuildRepostory.setBodilyForm(bodilyFormList);
    }

    @Test
    public void getWeight() {
        long time = System.currentTimeMillis();
        List<Weight> weightList = userBuildRepostory.getWeight(1, time - 3600 * 1000, time);

        assertNotEquals(0, weightList.size());
    }

    @Test
    public void getBodilyForm() {
        long time = System.currentTimeMillis();
        List<BodilyForm> bodilyForm = userBuildRepostory.getBodilyForm(1, time - 3600 * 1000, time);

        assertNotEquals(0, bodilyForm.size());
    }
}