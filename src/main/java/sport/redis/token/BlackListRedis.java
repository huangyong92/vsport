package sport.redis.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class BlackListRedis {

    @Autowired
    private JedisPool jedisPool;

    private static final String BLACK_KEY = "blcak_key";

    public void addToBlackList(String token) {
        Jedis jedis = jedisPool.getResource();

        jedis.sadd(BLACK_KEY, token);
    }

    public boolean isAtBlcakList(String token) {
        Jedis jedis = jedisPool.getResource();
        return jedis.sismember(BLACK_KEY, token);
    }
}
