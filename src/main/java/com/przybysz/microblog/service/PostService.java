package com.przybysz.microblog.service;

import com.przybysz.microblog.entity.Post;

import java.util.List;

public interface PostService {

    public List<Post> findAll();

    public Post findById(int id);

    public void save(Post post);

    public void deleteById(int id);

    //todo: find by id
//    find
//    save
//    delety by id
//    find user

}
