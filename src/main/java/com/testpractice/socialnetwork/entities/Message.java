package com.testpractice.socialnetwork.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private UserDTO  sender;
    private String  chat_id;
    private String content;
}

