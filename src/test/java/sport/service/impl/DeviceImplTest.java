package sport.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sport.redis.device.DeviceRedis;
import sport.service.DeviceService;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeviceImplTest {

    @Autowired
    private DeviceService mDeviceService;

    @Test
    public void updateDeviceStatu() {
        mDeviceService.updateDeviceStatu("1", "uvw", DeviceRedis.LOGIN);
    }

    @Test
    public void getCurrentDevice() {
        String user = mDeviceService.getCurrentDevice("1");

        assertEquals(null, user);
    }

    @Test
    public void getDevices() {
        Set<String> users = mDeviceService.getDevices("1");

        assertEquals(1, users.size());
    }
}