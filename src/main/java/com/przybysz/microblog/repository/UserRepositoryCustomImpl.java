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
        Collection<Role> roles = roleDAO.findRoleByName("ROLE_USER");

        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        entityManager.merge(user);

    }



}
