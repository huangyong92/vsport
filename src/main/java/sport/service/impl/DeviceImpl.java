package sport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sport.redis.device.DeviceRedis;
import sport.service.DeviceService;

import java.util.Set;

@Service
public class DeviceImpl implements DeviceService {

    @Autowired
    private DeviceRedis mDeviceRedis;

    //注册其实也就直接登录了，所以不需要单独的保存接口
    //todo 一个用户只有一个device的状态是登录的，需要做唯一性处理
    @Override
    public void updateDeviceStatu(String userId, String deviceId, String statu) {
        boolean isDeviceExit = mDeviceRedis.isExit(userId, deviceId);

        if (isDeviceExit) {
            mDeviceRedis.updateLoginStatu(userId, deviceId, statu);
        } else {
            mDeviceRedis.saveDeviceId(userId, deviceId, statu);
        }
    }

    @Override
    public String getCurrentDevice(String userId) {
        return mDeviceRedis.getCurrentDevice(userId);
    }

    @Override
    public Set<String> getDevices(String userId) {
        return mDeviceRedis.getDeviceIds(userId);
    }
}
