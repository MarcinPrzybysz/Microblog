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

import java.util.List;
import java.util.Optional;

//@RestController
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostService postService;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public String getUser(@PathVariable int userId, Model model){

        List<Post> posts = postService.findByUserId(userId);
        System.out.println(posts.get(1).getContent());


        User user = userRepository.findById(userId).get();


        model.addAttribute("user", user);
        model.addAttribute("posts", posts);

        return "user";
    }

    @GetMapping("/register")
    public String registerUser(Model model){
        User newUser = new User();
        model.addAttribute("user", newUser);

        return "register-form";
    }

    @GetMapping("/login")
    public String loginUser(){
        return "signin-form";
    }

    @PostMapping("addUser")
    public String addUser(@ModelAttribute("user") User user){
        user.setId(0);
        userRepository.save(user);
        return "redirect:/test/posts";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable int userId){
        Optional<User> user = userRepository.findById(userId);

        if(user==null){
            throw new RuntimeException("User with id not found: " + userId);
        }

        userRepository.deleteById(userId);
        return "Deleted user id: "+userId;
    }

}
