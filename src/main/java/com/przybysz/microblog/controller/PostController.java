package com.przybysz.microblog.controller;

import com.przybysz.microblog.config.MyUserDetails;
import com.przybysz.microblog.entity.Post;
import com.przybysz.microblog.entity.User;
import com.przybysz.microblog.repository.UserRepository;
import com.przybysz.microblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping(value="/posts", produces = "text/plain;charset=UTF-8")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("")
    public String findAll(Model model) {
        Post newPost = new Post();
        model.addAttribute("post", newPost);

        model.addAttribute("posts" , postService.findAll());
        return "list-posts";

    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable int postId) {
        Post post = postService.findById(postId);
        return post;
    }


    @PostMapping("/addPost")
    public String addPost(@ModelAttribute("employee") Post post) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;

        if (principal instanceof MyUserDetails) {
            username = ((MyUserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        post.setId(0);
        postService.save(post, username);

        // use a redirect to prevent duplicate submissions
        return "redirect:/posts";
    }


    //todo do zmiany metoda, nie jest potrzebny username
    @PutMapping("")
    public Post updatePost(@RequestBody Post post) {

        User user = new User("username", "pass", "email");
        user.setId(5);

        postService.save(post, "username");
        return post;
    }


    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable int postId) {

        Post post = postService.findById(postId);

        if (post == null) {
            throw new RuntimeException("Post id not found: " + postId);
        }
        postService.deleteById(postId);

        return "Deleted post id: " + postId;
    }


    @GetMapping("/plus/{id}")
    public String addRating(@PathVariable int id) {
        postService.addToRating(id);
        return "redirect:/posts";
    }

    @GetMapping("/minus/{id}")
    public String reduceRating(@PathVariable int id) {

        postService.reduceFromRating(id);
        return "redirect:/posts";
    }


}
