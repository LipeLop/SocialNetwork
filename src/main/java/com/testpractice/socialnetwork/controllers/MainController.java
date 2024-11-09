package com.testpractice.socialnetwork.controllers;

import com.testpractice.socialnetwork.entities.User;
import com.testpractice.socialnetwork.services.FriendShipService;
import com.testpractice.socialnetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"currentUser", "friends"})
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    FriendShipService friendShipService;

    @ModelAttribute(name = "friends")
    public List<User> getFriends(@ModelAttribute(name = "currentUser") User user) {
        return friendShipService.getFriends(user);
    }

    @ModelAttribute(name = "currentUser")
    public User user(Principal principal) throws Exception {
        System.out.println(  principal);
        return userService.findByLogin(principal.getName());
    }


    @ModelAttribute(name = "searchResults")
    public List<User> getUsers(@ModelAttribute(name = "friends") List<User> friends,
                               @ModelAttribute(name = "currentUser") User user) {
        List<User> users = new ArrayList<>(friends);
        users.add(user);
        return userService.findUsersNotInFriendsListAndNotMe(users);
    }


    @GetMapping("/home")
    public String showMainPage() {
        return "home";
    }
    @PostMapping("/addFriend")
    public String addFriend(@RequestParam Integer userId,
                            @ModelAttribute(name = "currentUser") User user,
                            @ModelAttribute(name = "friends") List<User> friends,
                            @ModelAttribute(name = "searchResults") List<User> searchResults) {
        User friend = userService.findById(userId);
        searchResults.remove(friend);
        friendShipService.addFriends(user, friend);
        friends.add(friend);
        return "home";
    }

}
