package com.przybysz.microblog.controller;

import com.przybysz.microblog.entity.User;
import com.przybysz.microblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable int userId){

        //set id 0 to make sure to set new id;
        User user = userService.findById(userId);
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
        userService.save(user);
        return "redirect:/test/posts";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable int userId){

        User user = userService.findById(userId);

        if(user==null){
            throw new RuntimeException("User with id not found: " + userId);
        }

        userService.deleteById(userId);

        return "Deleted user id: "+userId;
    }



}
