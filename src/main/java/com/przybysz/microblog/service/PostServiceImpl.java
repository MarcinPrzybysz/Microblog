package com.przybysz.microblog.service;

import com.przybysz.microblog.dao.PostDAO;
import com.przybysz.microblog.entity.Post;
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
    @Transactional
    public Post findById(int id) {
        return postDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Post post) {
        postDAO.save(post);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        postDAO.deleteById(id);
    }
}
