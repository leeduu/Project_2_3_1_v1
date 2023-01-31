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

    public UserDaoImpl(){}

//    private EntityManagerFactory entityManagerFactory;
//
//    public UserDaoImpl(EntityManagerFactory entityManagerFactory) {
//        this.entityManagerFactory = entityManagerFactory;
//    }
//
//    EntityManager entityManager = entityManagerFactory.createEntityManager();
//    EntityTransaction entityTransaction = entityManager.getTransaction();

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

//-------------------------------------------------------
//    private final JdbcTemplate jdbcTemplate;
//
//    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public List<User> showUsers() {
//        return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper<>(User.class));
//    }
//
//    @Override
//    public User showUser(int id) {
//        return jdbcTemplate.query("SELECT * FROM users WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(User.class))
//                .stream().findAny().orElse(null);
//    }
//
//    @Override
//    public void save(User user) {
//        jdbcTemplate.update("INSERT INTO users(first_name, last_name, email) VALUES(?, ?, ?)", user.getFirst_name(), user.getLast_name(), user.getEmail());
//    }
//
//    @Override
//    public void update(int id, User user) {
//        jdbcTemplate.update("UPDATE users SET first_name=?, last_name=?, email=? WHERE id=?", user.getFirst_name(), user.getLast_name(), user.getEmail(), id);
//    }
//
//
//    @Override
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
//    }








