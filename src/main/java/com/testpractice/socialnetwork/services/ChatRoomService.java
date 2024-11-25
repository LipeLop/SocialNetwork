package com.testpractice.socialnetwork.services;

import com.testpractice.socialnetwork.dtos.UserDto;
import com.testpractice.socialnetwork.entities.ChatRoom;
import com.testpractice.socialnetwork.reps.ChatRoomRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;



@Service
public class ChatRoomService {

    @Autowired
    ChatRoomRep chatRoomRep;

    public List<ChatRoom> getChatsForUser(String userId) {
        return chatRoomRep.findByMemberIdsContaining(userId);
    }
    public ChatRoom findChatsByChatRoomId(String roomId) {
        return chatRoomRep.findByChatId(roomId);
    }

    public List<ChatRoom> anotherWayGetChatsWithExactMembers(List<UserDto> users) {
        List<String> ids = users.stream().map(e -> String.valueOf(e.getId())).toList();
        List<ChatRoom> allChats = chatRoomRep.findByMemberIdsContaining(ids);
        return allChats.stream()
                .filter(chat -> chat.getMemberIds().size() == users.size())
                .collect(Collectors.toList());
    }
    public void saveChatRoom(String chatId, UserDto... user) {
        ChatRoom chatRoom = new ChatRoom();
        List<String> ids = Arrays.stream(user).map(e -> String.valueOf(e.getId())).toList();
        chatRoom.setMemberIds(ids);
        chatRoom.setChatId(chatId);
        chatRoomRep.save(chatRoom);

    }
    public String generateRandomChatId() {
        return UUID.randomUUID().toString();
    }
}

