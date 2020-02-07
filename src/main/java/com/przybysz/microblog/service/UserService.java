package com.przybysz.microblog.service;

import com.przybysz.microblog.entity.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();

    public User findById(int id);

    public void save(User user);

    public void deleteById(int id);
}
