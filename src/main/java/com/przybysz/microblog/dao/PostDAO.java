package com.przybysz.microblog.dao;

import com.przybysz.microblog.entity.Post;

import java.util.List;

public interface PostDAO {

    public List<Post> findAll();

    public Post findById(int id);

    public void save(Post post);

    public void deleteById(int id);

    //todo:...

}
