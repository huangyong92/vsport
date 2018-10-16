package sport.domain.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisServer;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import sport.config.RedisConfig;
import sport.domain.User;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository mUserRepository;

    @Autowired
    private JedisPool mRedisConfig;

    @Test
    public void addUser() {
        User user = new User();

        user.setMobile("13575732183");
        user.setNickName("Daniel");
        user.setAvatar("http://xxx.png");
        user.setGender(true);
        user.setLivingCity("杭州");

        int id = mUserRepository.addUser(user);

        assertNotEquals(0, id);
    }

    @Test
    public void updateUser() {
        User user = new User();

        user.setUserId(1);
        user.setAvatar("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2885559027,2082458421&fm=27&gp=0.jpg");
        mUserRepository.updateUser(user);
    }

    @Test
    public void findUserById() {
        User user = mUserRepository.findUserById(1);

        assertNotNull(user);
    }

    @Test
    public void findUserByPhone() {
        User user = mUserRepository.findUserByPhone("13575732183");

        assertNotNull(user);
    }

    @Test
    public void findUserByName() {
        List<User> userList = mUserRepository.findUserByName("n");

        assertNotEquals(0, userList);
    }

    @Test
    public void findUserBycity() {
        List<User> userList = mUserRepository.findUserBycity("杭州");

        assertNotEquals(0, userList);
    }

    @Test
    public void findUserByTarget() {
        List<User> userList = mUserRepository.findUserByTarget("");

        assertNotEquals(0, userList);
    }
}