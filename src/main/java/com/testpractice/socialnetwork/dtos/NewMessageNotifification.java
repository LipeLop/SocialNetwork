package com.testpractice.socialnetwork.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class NewMessageNotifification implements Serializable {
    private final int sender_id;
    private final String sender;
    private final String message;
}
