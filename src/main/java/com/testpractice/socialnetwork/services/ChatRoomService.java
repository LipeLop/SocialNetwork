package com.testpractice.socialnetwork.services;

import com.testpractice.socialnetwork.dtos.UserDto;
import com.testpractice.socialnetwork.entities.ChatRoom;
import com.testpractice.socialnetwork.reps.ChatRoomRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;



@Service
public class ChatRoomService {

    @Autowired
    ChatRoomRep chatRoomRep;

    public List<ChatRoom> getChatsForUser(int userId) {
        return chatRoomRep.findByMemberIdsContaining(userId);
    }
    public ChatRoom findChatsByChatRoomId(String roomId) {
        return chatRoomRep.findByChatId(roomId);
    }

    public List<ChatRoom> anotherWayGetChatsWithExactMembers(List<UserDto> users) {
        List<Integer> ids = users.stream().map(UserDto::getId).toList();
        List<ChatRoom> allChats = chatRoomRep.findByMemberIdsContaining(ids);
        return allChats.stream()
                .filter(chat -> chat.getMemberIds().size() == ids.size() &&
                        new HashSet<>(chat.getMemberIds()).equals(new HashSet<>(ids)))
                .collect(Collectors.toList());
    }
    public void saveChatRoom(String chatId, UserDto... user) {
        ChatRoom chatRoom = new ChatRoom();
        List<Integer> ids = Arrays.stream(user).map(UserDto::getId).toList();
        chatRoom.setMemberIds(ids);
        chatRoom.setChatId(chatId);
        chatRoomRep.save(chatRoom);

    }
    public ChatRoom saveChatRoom(ChatRoom chatRoom) {
        return chatRoomRep.save(chatRoom);  // Возвращаем сохраненный объект
    }
    public String generateRandomChatId() {
        return UUID.randomUUID().toString();
    }
}

