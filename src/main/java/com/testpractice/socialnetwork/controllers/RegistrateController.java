package com.testpractice.socialnetwork.controllers;

import com.testpractice.socialnetwork.entities.mysql.User;
import com.testpractice.socialnetwork.exceptions.UserIsAlreadyExist;
import com.testpractice.socialnetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrateController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // имя HTML-шаблона
    }

    @PostMapping("/register")
    public String registerUser(User user, Model model) {
        try {
            userService.register(user);
        } catch (UserIsAlreadyExist u){
            model.addAttribute("error", u.getMessage());
            return "register";
        }
        return "redirect:/login";
    }
}
