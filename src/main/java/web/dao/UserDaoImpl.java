package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
//-----------------------
    @PersistenceContext
    private final EntityManager entityManager;

    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> showUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User showUser(int id) {
        TypedQuery<User> userToShow = entityManager.createQuery("select u from User u where u.id = :id", User.class);
        userToShow.setParameter("id", id);
        return userToShow.getResultList().stream().findAny().orElse(null);
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional
    public void update(int id, User user) {
        entityManager.merge(user);
    }

    @Override
    @Transactional
    public void delete(int id) {
        User user = showUser(id);
        if (user != null) {
            entityManager.remove(user);
        }
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








