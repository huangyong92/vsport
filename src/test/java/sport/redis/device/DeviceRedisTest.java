package sport.redis.device;

import com.montnets.mwgate.common.GlobalParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceRedisTest {

    @Autowired
    private DeviceRedis mDeviceRedis;

    private final String mUserId = "135";

    @Test
    public void saveDeviceId() {
        mDeviceRedis.saveDeviceId(mUserId, "222");
    }

    @Test
    public void getDeviceIds() {
        Set<String> deviceIds = mDeviceRedis.getDeviceIds(mUserId);

        assertNotEquals(0, deviceIds);
    }
}