package sport.redis.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * 存储用户设备，及当前设备
 * 用来验证token
 */
@Component
public class DeviceRedis {

    @Autowired
    private JedisPool mJedisPool;

    private String getDeviceKey(String userId) {
        return "user:" + userId;
    }

    public void saveDeviceId(String userId, String deviceId) {
        Jedis jedis = mJedisPool.getResource();

        String deviceKey = getDeviceKey(userId);
        jedis.sadd(deviceKey, deviceId);
    }

    public Set<String> getDeviceIds(String userId) {
        Jedis jedis = mJedisPool.getResource();
        String deviceKey = getDeviceKey(userId);

        return jedis.smembers(deviceKey);
    }
}
