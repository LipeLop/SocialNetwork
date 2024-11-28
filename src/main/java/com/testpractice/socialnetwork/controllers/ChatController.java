package com.testpractice.socialnetwork.controllers;


import com.testpractice.socialnetwork.dtos.NewMessageNotifification;
import com.testpractice.socialnetwork.dtos.UserDto;
import com.testpractice.socialnetwork.entities.ChatRoom;
import com.testpractice.socialnetwork.dtos.MessageDTO;
import com.testpractice.socialnetwork.services.ChatRoomService;
import com.testpractice.socialnetwork.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;


@Controller

public class ChatController {


    @Autowired
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    ChatRoomService chatRoomService;

    @Autowired
    MessageService messageService;


    public ChatController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }


    @MessageMapping("/sendMessage")
    public void sendMessage(@Payload MessageDTO message) {
        message = messageService.saveMessageWithReturnId(message);
        messagingTemplate.convertAndSend("/user/" + message.getChatid() + "/queue/reply", message);
        NewMessageNotifification newMessageNotif = new NewMessageNotifification(message.getSender().getId(), message.getSender().getNickname(), message.getContent());
        sendNotificationToChatMembers(message.getChatid(), newMessageNotif);

    }

    public void sendNotificationToChatMembers(String chatroomId, NewMessageNotifification nof) {
        ChatRoom chats = chatRoomService.findChatsByChatRoomId(chatroomId);
        List<Integer> ids = chats.getMemberIds();
        for (int id : ids) {
            if (id != nof.getSender_id()) {
                messagingTemplate.convertAndSend("/user/" + id + "/queue/notifications", nof);
            }
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
            List<Integer> memberIds = List.of(currentUserDTO.getId(), friendDTO.getId());
            ChatRoom newChatRoom = new ChatRoom();
            newChatRoom.setMemberIds(memberIds);
            ChatRoom savedChatRoom = chatRoomService.saveChatRoom(newChatRoom);
            String newChatId = savedChatRoom.getChatId();
            model.addAttribute("chatRoom", newChatId);
        } else {
            ChatRoom chatRoom = chatRooms.get(0);
            model.addAttribute("chatRoom", chatRoom.getChatId());
            List<MessageDTO> messagesOfChat = messageService.getMessagesByChatId(chatRoom.getChatId());
            model.addAttribute("messagesOfChat", messagesOfChat);
        }
        model.addAttribute("friendDTO", friendDTO);
        model.addAttribute("currentUserDTO", currentUserDTO);
        return "messager";
    }
}

