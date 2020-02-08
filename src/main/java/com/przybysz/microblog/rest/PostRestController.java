package com.przybysz.microblog.rest;

import com.przybysz.microblog.entity.Post;
import com.przybysz.microblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@RestController
@Controller
@RequestMapping("/test")
public class PostRestController {

    private PostService postService;

    @Autowired
    public PostRestController(PostService postService) {
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

        model.addAttribute("posts" , postService.findAll());
        return "list-posts";

    }

    @GetMapping("/posts/{postId}")
    public Post getPost(@PathVariable int postId) {
        Post post = postService.findById(postId);
        return post;
    }

    @PostMapping("/posts")
    public Post addPost(@RequestBody Post post) {
        //todo w request body nie powinno być jeszcze user?

        //set id 0 to make sure to set new id;
        post.setId(0);
        postService.save(post);
        return post;
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
