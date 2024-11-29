package com.testpractice.socialnetwork.entities.mongo;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "chats")
public class ChatRoom {

    @Id
    private String chatId;

    private List<Integer> memberIds;

}