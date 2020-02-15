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


//@RestController
@Controller
@RequestMapping("/test")
public class PostController {

    private PostService postService;


    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/hello")
    public String sayHello(Model model) {
        model.addAttribute("date", new java.util.Date());
        return "index";
    }


    @GetMapping("/posts")
    public String findAll(Model model) {
//        List<Post> thePosts = postService.findAll();
//
//        System.out.println(thePosts);


        Post newPost = new Post();
        model.addAttribute("post", newPost);

        model.addAttribute("posts" , postService.findAll());
        return "list-posts";

    }

    @GetMapping("/posts/{postId}")
    public Post getPost(@PathVariable int postId) {
        Post post = postService.findById(postId);
        return post;
    }


    @PostMapping("/addPost")
    public String addPost(@ModelAttribute("employee") Post post) {

        System.out.println(post.toString());

        User user = new User("username", "pass", "firstName","lastName", "email");
        user.setId(5);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;

        if (principal instanceof MyUserDetails) {
            username = ((MyUserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }


        // save the employee
        post.setId(0);
        postService.save(post, username);

        // use a redirect to prevent duplicate submissions
        return "redirect:/test/posts";
    }


    //todo do zmiany metoda, nie jest potrzebny username
    @PutMapping("/posts")
    public Post updatePost(@RequestBody Post post) {

        User user = new User("username", "pass", "firstName","lastName", "email");
        user.setId(5);

        postService.save(post, "username");
        return post;
    }


    @DeleteMapping("/posts/{postId}")
    public String deletePost(@PathVariable int postId) {

        Post post = postService.findById(postId);

        if (post == null) {
            throw new RuntimeException("Post id not found: " + postId);
        }
        postService.deleteById(postId);

        return "Deleted post id: " + postId;
    }



}
