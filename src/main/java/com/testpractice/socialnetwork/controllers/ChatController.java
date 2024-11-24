package com.testpractice.socialnetwork.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.testpractice.socialnetwork.dtos.NewMessageNotifification;
import com.testpractice.socialnetwork.dtos.UserDto;
import com.testpractice.socialnetwork.entities.ChatRoom;
import com.testpractice.socialnetwork.entities.Message;
import com.testpractice.socialnetwork.services.ChatRoomService;
import com.testpractice.socialnetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
import java.util.UUID;


@Controller

public class ChatController {


    @Autowired
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    ChatRoomService chatRoomService;


    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload Message message) {
        messagingTemplate.convertAndSend("/user/" + message.getChatid() + "/queue/reply", message);
        NewMessageNotifification newMessageNotif = new NewMessageNotifification(message.getSender().getNickname(), message.getContent());
        sendNotificationToChatMembers(message.getChatid(), newMessageNotif);

    }
    public void sendNotificationToChatMembers(String chatroomId, NewMessageNotifification nof) {
        ChatRoom chats = chatRoomService.findChatsByChatRoomId(chatroomId);
        List<String> ids = chats.getMemberIds();
        for (String id : ids) {
            messagingTemplate.convertAndSend("/user/" + id + "/queue/notifications", nof);
        }

    }

    @GetMapping("/messager")
    public String chat(@RequestParam("friendId") int friendId,
                       @RequestParam("friendNickname") String friendNickname,
                       @SessionAttribute(name = "currentUser") UserDto currentUserDTO,
                       Model model) {
        UserDto friendDTO = new UserDto(friendId, friendNickname);
        List<ChatRoom> chatRooms = chatRoomService.anotherWayGetChatsWithExactMembers(List.of(currentUserDTO, friendDTO));
        if (chatRooms.isEmpty()) {
            String newChatId = generateRandomChatId();
            model.addAttribute("chatRoom", newChatId);
            chatRoomService.saveChatRoom(newChatId, currentUserDTO, friendDTO);
        } else {
            ChatRoom chatRoom = chatRooms.get(0);
            model.addAttribute("chatRoom", chatRoom.getChatId());
        }
        model.addAttribute("friendDTO", friendDTO);
        model.addAttribute("currentUserDTO", currentUserDTO);
        return "messager";
    }

    private String generateRandomChatId() {
        return UUID.randomUUID().toString();
    }
}

