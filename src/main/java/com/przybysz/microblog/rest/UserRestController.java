package com.przybysz.microblog.rest;

import com.przybysz.microblog.entity.User;
import com.przybysz.microblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.DescriptorKey;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
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

    @PostMapping("")
    public User addUser(@RequestBody User user){
        user.setId(0);
        userService.save(user);
        return user;
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
