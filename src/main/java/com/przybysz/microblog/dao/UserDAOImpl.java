package com.przybysz.microblog.dao;

import com.przybysz.microblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> findAll() {

        Query query = entityManager.createQuery("from User");

        List<User> users = query.getResultList();

        return users;
    }

    @Override
    public User findById(int id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public void save(User user) {
        User dbUser = entityManager.merge(user);
        user.setId(dbUser.getId());
    }

    @Override
    public void deleteById(int id) {
        Query query = entityManager.createQuery("delete from User where id=:userId");

        query.setParameter("userId", id);

        query.executeUpdate();
    }
}
