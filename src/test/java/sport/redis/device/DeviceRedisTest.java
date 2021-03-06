package sport.redis.device;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sport.enums.LoginEnum;

import java.util.Set;

import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceRedisTest {

    @Autowired
    private DeviceRedis mDeviceRedis;

    private final String mUserId = "Daniel135";

    @Test
    public void saveDeviceId() {
        mDeviceRedis.saveDeviceId(mUserId, "1314", LoginEnum.LOGIN.getStatu());
    }

    @Test
    public void getDeviceIds() {
        Set<String> deviceIds = mDeviceRedis.getDeviceIds(mUserId);

        assertNotEquals(0, deviceIds);
    }

    @Test
    public void updateLoginStatu() {
        mDeviceRedis.updateLoginStatu(mUserId, "12345", LoginEnum.LOGIN.getStatu());
    }

    @Test
    public void getCurrentDevice() {
        String deviceId = mDeviceRedis.getCurrentDevice(mUserId);

        assertNotEquals(null, deviceId);
    }

    @Test
    public void isExit() {
        Boolean isExit = mDeviceRedis.isExit(mUserId, "12345");

        assertNotEquals(false, isExit);
    }
}