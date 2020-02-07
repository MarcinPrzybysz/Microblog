package com.przybysz.microblog.dao;

import com.przybysz.microblog.entity.User;

import java.util.List;

public interface UserDAO {

    public List<User> findAll();

    public User findById(int id);

    public void save(User user);

    public void deleteById(int id);
}
