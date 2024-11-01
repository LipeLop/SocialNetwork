package com.testpractice.socialnetwork.controllers;

import com.testpractice.socialnetwork.entities.User;
import com.testpractice.socialnetwork.services.FriendShipService;
import com.testpractice.socialnetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"user", "friends"})
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    FriendShipService friendShipService;

    @ModelAttribute(name = "user")
    public User user(Principal principal) throws Exception {
        return userService.findByLogin(principal.getName());

    }
    @ModelAttribute(name = "searchResults")
    public List<User> getUsers(Principal principal) {
        return userService.findAllExceptMe(principal.getName());
    }
    @ModelAttribute(name = "friends")
    public List<User> getFriends(Principal principal) {
        List<User> friends = new ArrayList<>();
        return friends;
    }

    @GetMapping("/home")
    public String showMainPage() {
        return "home";
    }
    @GetMapping("/search")
    public String search(@RequestParam String name, @ModelAttribute(name = "user") User user, Model model) {
        List<User> searchResults = userService.findAllByNameExceptMe(name, user.getLogin());
        model.addAttribute("searchResults", searchResults);
        return "home";
    }
    @PostMapping("/addFriend")
    public String addFriend(@RequestParam Integer userId,
                            @ModelAttribute(name = "user") User user,
                            @ModelAttribute(name = "friends") List<User> friends,
                            @ModelAttribute(name = "searchResults") List<User> searchResults) {
        User friend = userService.findById(userId);
        searchResults.remove(friend);
        friendShipService.addFriends(user, friend);
        friends.add(friend);
        return "home";
    }

}
