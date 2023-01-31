package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.dao.UserDaoImpl;
import web.model.User;

import javax.transaction.SystemException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

//    @Transactional(readOnly = true)
    @Override
    public List<User> showUsers() {
        return userDao.showUsers();
    }

//    @Transactional(readOnly = true)
    @Override
    public User showUser(int id) {
        return userDao.showUser(id);
    }

    @Transactional
    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Transactional
    @Override
    public void update(int id, User user){
        userDao.update(id, user);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
}
