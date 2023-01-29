package web.service;

import org.springframework.stereotype.Service;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> showUsers() {
        return userDao.showUsers();
    }

    @Override
    public User showUser(int id) {
        return userDao.showUser(id);
    }

    @Override
    public void save(User user){
        userDao.save(user);
    }

    @Override
    public void update(int id, User user){
        userDao.update(id, user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
}
