package com.przybysz.microblog.controller;

import com.przybysz.microblog.entity.User;
import com.przybysz.microblog.repository.UserRepository;
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

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public Optional<User> getUser(@PathVariable int userId){

        //set id 0 to make sure to set new id;
        Optional<User> user = userRepository.findById(userId);
        return user;
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
