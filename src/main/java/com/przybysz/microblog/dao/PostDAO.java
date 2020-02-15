package com.przybysz.microblog.dao;

import com.przybysz.microblog.entity.Post;
import com.przybysz.microblog.entity.User;

import java.util.List;

public interface PostDAO {

    public List<Post> findAll();

    public List<Post> findByUserId(int userId);

    public Post findById(int id);

    public void save(Post post, String username);

    public void deleteById(int id);

    //todo:...

}
