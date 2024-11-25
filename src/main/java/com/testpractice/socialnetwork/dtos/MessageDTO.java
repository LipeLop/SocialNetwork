package com.testpractice.socialnetwork.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO {

    private int id;
    private UserDto sender;
    private String  chatid;
    private String content;

    public MessageDTO(UserDto sender, String chatid, String content) {
        this.chatid = chatid;
        this.content = content;
        this.sender = sender;
    }
}

