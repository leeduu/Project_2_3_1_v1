package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> showUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User showUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(int id, User user) {
        User userToUpdate = showUser(id);
        userToUpdate.setFirst_name(user.getFirst_name());
        userToUpdate.setLast_name(user.getLast_name());
        userToUpdate.setEmail(user.getEmail());
        entityManager.merge(userToUpdate);
    }

    @Override
    @Transactional
    public void delete(int id) {
        entityManager.remove(entityManager.find(User.class, id))/* ? showUser(id) : entityManager.merge(showUser(id)))*/;
    }

}
