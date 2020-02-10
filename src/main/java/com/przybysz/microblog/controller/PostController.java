package com.przybysz.microblog.controller;

import com.przybysz.microblog.entity.Post;
import com.przybysz.microblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

        // save the employee
        post.setId(0);
        postService.save(post);

        // use a redirect to prevent duplicate submissions
        return "redirect:/test/posts";
    }



    @PutMapping("/posts")
    public Post updatePost(@RequestBody Post post) {

        postService.save(post);
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
