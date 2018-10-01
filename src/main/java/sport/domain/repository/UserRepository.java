package sport.domain.repository;

import sport.domain.User;

import java.util.List;

public interface UserRepository {

    int addUser(User user);

    void updateUser(User user);

    User findUserById(Integer id);

    User findUserByPhone(String phone);

    List<User> findUserByName(String name);

    List<User> findUserBycity(String city);

    List<User> findUserByTarget(String target);
}
