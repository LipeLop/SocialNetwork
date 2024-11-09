package com.testpractice.socialnetwork.controllers;

import com.testpractice.socialnetwork.entities.User;
import com.testpractice.socialnetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/search")
@SessionAttributes({"currentUser", "friends"})
public class SeacrhController {

    @Autowired
    UserService userService;

    @GetMapping()
    public String search(@RequestParam String name,
                         @ModelAttribute(name = "currentUser") User user,
                         @ModelAttribute(name = "friends") List<User> friends,
                         Model model) {
        List<User> users = new ArrayList<>(friends);
        users.add(user);
        List<User> searchResults = userService.findAllByNameExceptMeandFriends(name, users);
        model.addAttribute("searchResults", searchResults);
        return "home";
    }
}
