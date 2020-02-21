package com.przybysz.microblog.controller;

import com.przybysz.microblog.entity.Post;
import com.przybysz.microblog.entity.User;
import com.przybysz.microblog.repository.UserRepository;
import com.przybysz.microblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
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
        User user = userRepository.findById(userId).get();

        model.addAttribute("user", user);
        model.addAttribute("posts", posts);

        return "user";
    }

    @PostMapping("addUser")
    public String addUser(@ModelAttribute("user") User user, Model model){

        Optional<User> newUser = userRepository.findByUsername(user.getUsername());


        if(newUser.isPresent()){
            model.addAttribute("user",new User());
            model.addAttribute("inputError","Username already taken");
            return "register-form";
        }

        //todo: metoda find by email i  validacja istniejącego maila


        user.setId(0);
        userRepository.addUser(user);

        return "redirect:/posts";
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
