package com.przybysz.microblog.repository;

import com.przybysz.microblog.entity.Authority;
import com.przybysz.microblog.entity.Post;
import com.przybysz.microblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void addUser(User user) {
//
//        if (emailExist(accountDto.getEmail())) {
//            throw new EmailExistsException(
//                    "There is an account with that email adress:" + accountDto.getEmail());
//        }

        System.out.println("add user in repo");
        user.setAuthorities(new Authority(user));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setAvatar("default.png");

        Authority authority = new Authority("ROLE_TEST");

        user.setAuthorities(authority);

        System.out.println(user.toString());
        User dbUser = entityManager.merge(user);
       



    }



}
