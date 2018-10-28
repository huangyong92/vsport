package sport.service;

import sport.domain.User;

import java.util.List;

public interface UserService {

    int addUser(User user);

    User findUserByPhone(String phone);

    List<User> findUserByName(String name);

    User findUserById(String uid);

    void updateUser(User user);

    String getUserIdByPassword(String name, String password);

    boolean modifyPassword(String phone, String password);
}
