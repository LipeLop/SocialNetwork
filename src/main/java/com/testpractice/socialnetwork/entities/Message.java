package com.testpractice.socialnetwork.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String sender;
    private String receiver;  // ID получателя
    private String content;


}

