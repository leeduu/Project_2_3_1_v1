package web.dao;

import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

//    private ArrayList<User> users;
    private static int USERS_COUNT;

//    {
//        users = new ArrayList<>();
//        users.add(new User("Oleg", "Odintsov", "oo@gmail.com", ++USERS_COUNT));
//        users.add(new User("Klara", "Korovina", "kk@gmail.com", ++USERS_COUNT));
//        users.add(new User("Lana", "Lykova", "ll@gmail.com", ++USERS_COUNT));
//        users.add(new User("Erika", "Ermolova", "ee@gmail.com", ++USERS_COUNT));
//        users.add(new User("Semen", "Sokolov", "ss@gmail.com", ++USERS_COUNT));
//        users.add(new User("Jane", "Jungen", "jj@gmail.com", ++USERS_COUNT));
//        users.add(new User("Neil", "Novikov", "nn@gmail.com", ++USERS_COUNT));
//    }

    @Override
    public List<User> showUsers() {
        return users;
    }

    @Override
    public User showUser(int id) {
//        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }

    @Override
    public void save(User user) {
        user.setId(++USERS_COUNT);
        users.add(user);
    }

    @Override
    public void update(int id, User updatedUser) {
//        User userToBeUpdated = showUser(id);
//
//        userToBeUpdated.setFirst_name(updatedUser.getFirst_name());
//        userToBeUpdated.setLast_name(updatedUser.getLast_name());
//        userToBeUpdated.setEmail(updatedUser.getEmail());
    }

    @Override
    public void delete(int id) {
        users.removeIf(u -> u.getId() == id);
    }
}
