package com.testpractice.socialnetwork.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testpractice.socialnetwork.dtos.NewMessageNotifification;
import com.testpractice.socialnetwork.dtos.UserDto;
import com.testpractice.socialnetwork.entities.Message;
import com.testpractice.socialnetwork.entities.User;
import com.testpractice.socialnetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller

public class ChatController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    private UserService userService;

    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }



    @MessageMapping("/sendMessage/{chatId}")
    public void sendMessage(@DestinationVariable String chatId, @Payload Message message) {
        messagingTemplate.convertAndSend("/user/" + chatId + "/queue/reply", message);
        NewMessageNotifification newMessageNotif = new NewMessageNotifification(message.getSender().getNickname(), message.getContent());
        messagingTemplate.convertAndSend("/user/" + message.getReceiver().getId() + "/queue/notifications", newMessageNotif);

    }

    @GetMapping("/messager")
    public String chat(@RequestParam("friendId") int friendId,
                       @RequestParam("friendNickname") String friendNickname,
                       @SessionAttribute(name = "currentUser") UserDto currentUserDTO,
                       Model model){
        UserDto friendDTO = new UserDto(friendId, friendNickname);
        model.addAttribute("friendDTO", friendDTO);
        model.addAttribute("currentUserDTO", currentUserDTO);
        return "messager";
    }
}

