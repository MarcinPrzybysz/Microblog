package com.przybysz.microblog.dao;

import com.przybysz.microblog.entity.Post;
import com.przybysz.microblog.entity.User;
import com.przybysz.microblog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
public class PostDAOImpl implements PostDAO {

    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public PostDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Post> findAll() {
        // create a query
        Query query =
                entityManager.createQuery("from Post");

        // execute query and get result list
        List<Post> posts = query.getResultList();
        orderByDate(posts);

        return posts;
    }

    @Override
    public List<Post> findByUserId(int userId) {
        Query query = entityManager.createQuery("from Post WHERE user_id=:userId");
        query.setParameter("userId", userId);
        List<Post> posts = query.getResultList();
        orderByDate(posts);

        return posts;
    }

    @Override
    public Post findById(int id) {
        Post post = entityManager.find(Post.class, id);

        return post;
        //todo: catch exceptions
    }


    @Override
    public void save(Post post, String username) {
        //Save or update(if id=0)
        if (post.getId() == 0) {
            LocalDateTime now = LocalDateTime.now();
            post.setDate(now.toString());
        }

        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new RuntimeException("Cannot save post with null User: " );
        }

        post.setUser(user);

        Post dbPost = entityManager.merge(post);

        //Updates id
        post.setId(dbPost.getId());
        //todo: catch exceptions
    }

    @Override
    public void deleteById(int id) {
        Query query = entityManager.createQuery("delete from Post where id=:postId");

        query.setParameter("postId", id);

        query.executeUpdate();

        //todo: catch exceptions
    }


    private List<Post> orderByDate(List<Post> posts) {
        Comparator<Post> compareByDate = (Post p1, Post p2) -> p1.getDate().compareTo(p2.getDate());
        Collections.sort(posts, compareByDate);

        Collections.reverse(posts);
        return posts;
    }

    @Override
    public void addToRating(int postId) {
        Query query = entityManager.createQuery("UPDATE Post set rating = rating+1 WHERE id=:id");
        query.setParameter("id", postId);
        query.executeUpdate();

    }

    @Override
    public void reduceFromRating(int postId) {
        Query query = entityManager.createQuery("UPDATE Post set rating = rating-1 WHERE id=:id");
        query.setParameter("id", postId);
        query.executeUpdate();
    }

}
