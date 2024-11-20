package com.testpractice.socialnetwork.entities;

import com.testpractice.socialnetwork.dtos.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private UserDto sender;
    private UserDto  receiver;
    private String content;
}

