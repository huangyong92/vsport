package sport.domain.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.JedisPool;
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
        User user1 = new User();

        user1.setPhone("13575732183");
        user1.setNickName("Daniel");
        user1.setPassword("555");
        user1.setAvatar("http://xxx.png");
        user1.setGender(1);
        user1.setLivingCity("杭州");

        User user2 = new User();
        user2.setPhone("13588199148");
        user2.setNickName("小丫头");
        user2.setPassword("123");
        user2.setAvatar("http://xxx2.png");
        user2.setGender(0);
        user2.setLivingCity("杭州");

        User user3 = new User();
        user3.setPhone("13738766778");
        user3.setNickName("小勇");
        user3.setPassword("123");
        user3.setAvatar("http://xxx2.png");
        user3.setGender(1);
        user3.setLivingCity("杭州");

        User user4 = new User();
        user4.setPhone("1111111111");
        user4.setNickName("小太阳");
        user4.setPassword("123");
        user4.setAvatar("http://xxx2.png");
        user4.setGender(1);
        user4.setLivingCity("杭州");

        int id = mUserRepository.addUser(user1);
        mUserRepository.addUser(user2);
        mUserRepository.addUser(user3);
        mUserRepository.addUser(user4);

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

    @Test
    public void getPassword() {
        String userId = mUserRepository.findUserByPhoneAndPassword("Daniel", "555");

        assertNotEquals(null, userId);
    }
}