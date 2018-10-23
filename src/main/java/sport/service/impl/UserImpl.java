package sport.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sport.domain.User;
import sport.domain.repository.UserRepository;
import sport.service.UserService;

import java.util.List;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository mUserRepository;

    @Override
    public void addUser(User user) {
        mUserRepository.addUser(user);
    }

    //todo 可能要同步到redis
    @Override
    public User findUserByPhone(String phone) {
        return mUserRepository.findUserByPhone(phone);
    }

    @Override
    public List<User> findUserByName(String name) {
        return mUserRepository.findUserByName(name);
    }

    @Override
    public User findUserById(String uid) {
        return mUserRepository.findUserById(Integer.parseInt(uid));
    }

    @Override
    public void updateUser(User user) {
        mUserRepository.updateUser(user);
    }

    @Override
    public boolean verifyUserByPassword(String name, String myPassword) {
        List<String> passwordList = mUserRepository.getPassword(name);

        for (String password:
                passwordList) {
            if (password.equals(myPassword)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean modifyPassword(String phone, String password) {
        User user = mUserRepository.findUserByPhone(phone);
        if (user != null) {
            mUserRepository.updatePassword(user.getUserId(), password);

            return true;
        }

        return false;
    }
}
