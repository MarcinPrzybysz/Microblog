package com.przybysz.microblog.controller;

import com.przybysz.microblog.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class LoginController {

    @GetMapping("/register")
    public String registerUser(Model model) {
        User newUser = new User();
        model.addAttribute("user", newUser);
        return "register-form";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "signin-form";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {
        System.out.println("test");
        return "access-denied";
    }
}
