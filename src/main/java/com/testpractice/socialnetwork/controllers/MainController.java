package com.testpractice.socialnetwork.controllers;

import com.testpractice.socialnetwork.dtos.UserDto;
import com.testpractice.socialnetwork.entities.User;
import com.testpractice.socialnetwork.mappers.UserMapper;
import com.testpractice.socialnetwork.services.FriendShipService;
import com.testpractice.socialnetwork.services.UserDTOService;
import com.testpractice.socialnetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"currentUser", "friends"})
public class MainController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserDTOService userDTOService;

    @Autowired
    UserService userService;

    @Autowired
    FriendShipService friendShipService;

    @ModelAttribute(name = "friends")
    public List<UserDto> getFriends(@ModelAttribute(name = "currentUser") UserDto user) {
        return friendShipService.getFriends(user);
    }

    @ModelAttribute(name = "currentUser")
    public UserDto user(@AuthenticationPrincipal User user) throws Exception {
        return userMapper.toUserDto(user);
    }


    @ModelAttribute(name = "searchResults")
    public List<UserDto> getUsers(@ModelAttribute(name = "friends") List<UserDto> friends,
                               @ModelAttribute(name = "currentUser") UserDto user) {
        List<UserDto> users = new ArrayList<>(friends);
        users.add(user);
        return userDTOService.findUsersNotInFriendsListAndNotMe(users);
    }


    @GetMapping("/home")
    public String showMainPage() {
        return "home";
    }
    @PostMapping("/addFriend")
    public String addFriend(@RequestParam Integer userId,
                            @ModelAttribute(name = "currentUser") UserDto user,
                            @ModelAttribute(name = "friends") List<UserDto> friends,
                            @ModelAttribute(name = "searchResults") List<UserDto> searchResults) {
        UserDto friend = userDTOService.findById(userId);
        searchResults.remove(friend);
        friendShipService.addFriends(user, friend);
        friends.add(friend);
        return "home";
    }

}
