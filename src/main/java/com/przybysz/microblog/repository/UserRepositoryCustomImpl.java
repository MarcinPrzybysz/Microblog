package com.przybysz.microblog.repository;

import com.przybysz.microblog.dao.RoleDAO;
import com.przybysz.microblog.entity.Role;
import com.przybysz.microblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    public UserRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        System.out.println("addUser in repo");

        Collection<Role> roles = roleDAO.findRoleByName("ROLE_USER");
        System.out.println("ściągniete role");

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        entityManager.merge(user);
    }

//    @Override
//    public User findByUsername(String username) {
//
//        System.out.println("find user by username");
//        List<User> users = null;
//        User user= null;
//        Query query = entityManager.createQuery("FROM User WHERE username=:username");
//
//        query.setParameter("username", username);
//
//
//        users = query.getResultList();
//
//        if(!users.isEmpty()){
//           user = users.get(0);
//            System.out.println("znaleziony: " +user.toString());
//           return user;
//        }
//
//        return null;
//    }
}
