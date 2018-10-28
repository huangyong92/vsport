package sport.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sport.domain.User;
import sport.service.UserService;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void addUser() {
        User user = new User();
        user.setNickName("文文");
        user.setGender(0);
        user.setPassword("wenwen123");
        user.setPhone("13588199148");
        user.setAvatar("www.xxx.com");

        userService.addUser(user);
    }

    @Test
    public void findUserByPhone() {
        User user = userService.findUserByPhone("13588199148");

        assertNotEquals(null, user);
    }

    @Test
    public void findUserByName() {
        List<User> user = userService.findUserByName("文文");

        assertNotEquals(0, user.size());
    }

    @Test
    public void findUserById() {
        User user = userService.findUserById("1");

        assertNotEquals(null, user);
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setAvatar("www.abc.com");
        user.setUserId(1);

        userService.updateUser(user);
    }

    @Test
    public void verifyUserByPassword() {
        String userId = userService.getUserIdByPassword("文文", "wenwen123");

        assertNotEquals(null, userId);
    }

    @Test
    public void modifyPassword() {
        boolean isSuccess = userService.modifyPassword("13588199148", "wewen++");
        assertEquals(true, isSuccess);
    }
}