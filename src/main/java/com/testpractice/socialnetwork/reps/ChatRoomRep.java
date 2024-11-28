package com.testpractice.socialnetwork.reps;

import com.testpractice.socialnetwork.entities.ChatRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ChatRoomRep extends MongoRepository<ChatRoom, String> {

    List<ChatRoom> findByMemberIdsContaining(int userId);

    ChatRoom findByChatId(String chatId);
    List<ChatRoom> findByMemberIdsContaining(List<Integer> userIds);
}
