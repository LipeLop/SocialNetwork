package com.testpractice.socialnetwork.controllers;

import com.testpractice.socialnetwork.dtos.UserDto;
import com.testpractice.socialnetwork.entities.User;
import com.testpractice.socialnetwork.services.UserDTOService;
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
    private UserDTOService userDTOService;

    @GetMapping()
    public String search(@RequestParam String name,
                         @ModelAttribute(name = "currentUser") UserDto user,
                         @ModelAttribute(name = "friends") List<UserDto> friends,
                         Model model) {
        List<UserDto> users = new ArrayList<>(friends);
        users.add(user);
        List<UserDto> searchResults = userDTOService.findAllByNameExceptMeandFriends(name, users);
        model.addAttribute("searchResults", searchResults);
        return "home";
    }
}
