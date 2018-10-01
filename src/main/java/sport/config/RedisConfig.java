package sport.config;

import lombok.Data;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;

@Component
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedisConfig {

    public String host;

    public Integer port;

    @Bean(name = "jedis.pool", autowire = Autowire.BY_TYPE)
//    @Bean(name = "JedisPool", autowire = Autowire.BY_NAME)
    public JedisPool getJedisPool() {
        return new JedisPool(host, port);
    }
}
