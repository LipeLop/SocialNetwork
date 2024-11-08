package com.testpractice.socialnetwork.controllers;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/message")
public class MessageController {

    @PostMapping()
    public String message(@RequestParam(name = "userId") Long userId, @RequestParam String content) {
        System.out.println(content);
        System.out.println(userId);
        return "message";
    }
}
