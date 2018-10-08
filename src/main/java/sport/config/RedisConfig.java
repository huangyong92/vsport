package sport.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedisConfig {

    public String host;

    public Integer port;

    @Value("${spring.redis.jedis.pool.max-active}")
    public Integer maxActive;

    @Value("${spring.redis.jedis.pool.min-idle}")
    public Integer minIdel;

//    @Value("${spring.redis.jedis.pool.max-wait}")
//    public String maxWait;

    @Bean(name = "jedis.pool", autowire = Autowire.BY_TYPE)
//    @Bean(name = "JedisPool", autowire = Autowire.BY_NAME)
    public JedisPool getJedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMinIdle(minIdel);
        jedisPoolConfig.setMaxWaitMillis(10000);
        jedisPoolConfig.setTestOnBorrow(true);

        JedisPool jedisPool =  new JedisPool(jedisPoolConfig, host, port);
        return jedisPool;
    }
}
