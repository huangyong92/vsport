package sport.domain.repository;

import org.apache.ibatis.annotations.Param;
import sport.domain.User;

import java.util.List;

public interface UserRepository {

    int addUser(User user);

    void updateUser(User user);

    void updatePassword(@Param("userId") Integer userId,
                                @Param("password") String password);

    User findUserById(Integer id);

    User findUserByPhone(String phone);

    List<User> findUserByName(String name);

    List<User> findUserBycity(String city);

    List<User> findUserByTarget(String target);

    List<String> getPassword(String name);
}
