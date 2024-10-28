package com.testpractice.socialnetwork.controllers;

import com.testpractice.socialnetwork.entities.User;
import com.testpractice.socialnetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // имя HTML-шаблона
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        userService.register(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // имя HTML-шаблона
    }
    @GetMapping("/home")
    public String showMainPage() {
        return "home";
    }
}