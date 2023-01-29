package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    List<User> showUsers();
    User showUser(int id);
    void save(User user);
    void update(int id, User user);
    void delete(int id);
}
