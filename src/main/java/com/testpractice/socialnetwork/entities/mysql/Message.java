package com.testpractice.socialnetwork.entities.mysql;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "message")
public class Message {
    @Id
    @Column(name = "idmessage", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @Size(max = 45)
    @NotNull
    @Column(name = "chat_id", nullable = false, length = 45)
    private String chatId;

    @NotNull
    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    public Message(User sender, String chatId, String content) {
        this.chatId = chatId;
        this.content = content;
        this.sender = sender;
    }
}