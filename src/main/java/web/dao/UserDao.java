package web.dao;

import web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {

    List<User> showUsers() throws SQLException;
    User showUser(int id);
    void save(User user) throws SQLException;
    void update(int id, User user);
    void delete(int id);
}
