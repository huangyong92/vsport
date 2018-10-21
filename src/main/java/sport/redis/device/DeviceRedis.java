package sport.redis.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * 存储用户设备，及当前设备
 * userId appId loginstatu
 */
@Component
public class DeviceRedis {

    @Autowired
    private JedisPool mJedisPool;

    public static final String LOGIN = "1";
    public static final String LOGOUT = "0";


    private String getDeviceKey(String userId) {
        return "user:" + userId;
    }

    private String getLoginStatuKey(String userId, String deviceId) {
        return "user_device:" + userId + "_" + deviceId;
    }

    public void saveDeviceId(String userId, String deviceId) {
        Jedis jedis = mJedisPool.getResource();

        if (!isExit(userId, deviceId)) {
            String deviceKey = getDeviceKey(userId);
            jedis.sadd(deviceKey, deviceId);

            updateLoginStatu(userId, deviceId, LOGOUT);
        }
    }

    public void updateLoginStatu(String userId, String deviceId, String loginStatu) {
        Jedis jedis = mJedisPool.getResource();

        String loginStatuKey = getLoginStatuKey(userId, deviceId);
        jedis.set(loginStatuKey, loginStatu);
    }

    public Set<String> getDeviceIds(String userId) {
        Jedis jedis = mJedisPool.getResource();
        String deviceKey = getDeviceKey(userId);

        return jedis.smembers(deviceKey);
    }

    public String getCurrentDevice(String userId) {
        Jedis jedis = mJedisPool.getResource();

        Set<String> deviceIds = getDeviceIds(userId);
        for (String device :
                deviceIds) {
            String loginKey = getLoginStatuKey(userId, device);
            String loginStatu = jedis.get(loginKey);

            if (loginStatu.equals(LOGIN)) {
                return device;
            }
        }

        return null;
    }

    public boolean isExit(String userId, String deviceId) {
        Jedis jedis = mJedisPool.getResource();

        String deviceKey = getDeviceKey(userId);
        return jedis.sismember(deviceKey, deviceId);
    }
}
