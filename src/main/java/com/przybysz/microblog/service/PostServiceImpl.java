package com.przybysz.microblog.service;

import com.przybysz.microblog.dao.PostDAO;
import com.przybysz.microblog.entity.Post;
import com.przybysz.microblog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private PostDAO postDAO;

    @Autowired
    public PostServiceImpl(PostDAO postDAO) {
        this.postDAO = postDAO;
    }

    @Override
    @Transactional
    public List<Post> findAll() {
        return postDAO.findAll();
    }

    @Override
    public List<Post> findByUserId(int userId) {
        return postDAO.findByUserId(userId);
    }

    @Override
    @Transactional
    public Post findById(int id) {
        return postDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Post post, String username) {
        postDAO.save(post, username);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        postDAO.deleteById(id);
    }

    @Override
    @Transactional
    public void addToRating(int postId) {
        postDAO.addToRating(postId);
    }

    @Override
    @Transactional
    public void reduceFromRating(int postId) {
        postDAO.reduceFromRating(postId);
    }
}
