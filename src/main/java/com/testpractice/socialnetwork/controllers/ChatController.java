package com.testpractice.socialnetwork.controllers;


import com.testpractice.socialnetwork.entities.Message;
import com.testpractice.socialnetwork.entities.User;
import com.testpractice.socialnetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@SessionAttributes("currentUser")
public class ChatController {

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
    }

    @GetMapping("/messager")
    public String chat(@RequestParam("receiverId") String receiverId,
                       @ModelAttribute(name = "currentUser") User user,
                       Model model) {
        User receiver = userService.getUserById(Integer.parseInt(receiverId));

        model.addAttribute("currentUser", String.valueOf(user.getId()));
        model.addAttribute("receiver", String.valueOf(receiver.getId()));

        return "messager";  // Страница чата, где можно отправить сообщение
    }

}

