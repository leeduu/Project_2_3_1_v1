package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;
import web.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private static int USERS_COUNT;

    public UserDaoImpl(){}
    private static DBConnection dbConnection = new DBConnection();
    private static Connection connection = (Connection) dbConnection.entityManagerFactory();

    @Override
    public List<User> showUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM users";
            ResultSet rs = statement.executeQuery(SQL);

            while(rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User showUser(int id) {
        User user = null;
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            user = new User();
            user.setFirst_name(rs.getString("first_name"));
            user.setLast_name(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public void save(User user) throws SQLException {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO users VALUES(10, ?, ?, ?)");
        ps.setString(1, user.getFirst_name());
        ps.setString(2, user.getLast_name());
        ps.setString(3, user.getEmail());
        ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
        }
    }

    @Override
    public void update(int id, User user) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE users SET first_name=?, last_name=?, email=?");
            ps.setString(1, user.getFirst_name());
            ps.setString(2, user.getLast_name());
            ps.setString(3, user.getEmail());
            ps.setInt(id, ++USERS_COUNT);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


//    private ArrayList<User> users;
//    private static int USERS_COUNT;

//    @Override
//    public User showUser(int id) {
//        return (User) entityManager.find(User.class, id);
//        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
//    }

//    @Override
//    public void save(User user) throws SQLException {
//        //user.setId(++USERS_COUNT);
//        //users.add(user);
//    }

//    @Override
//    public void update(int id, User updatedUser) {
//        User userToBeUpdated = showUser(id);
//        userToBeUpdated.setFirst_name(updatedUser.getFirst_name());
//        userToBeUpdated.setLast_name(updatedUser.getLast_name());
//        userToBeUpdated.setEmail(updatedUser.getEmail());
//    }

//    @Override
//    public void delete(int id) {
//        //users.removeIf(u -> u.getId() == id);
//    }
}
