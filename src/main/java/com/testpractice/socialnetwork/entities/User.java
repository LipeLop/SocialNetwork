package com.testpractice.socialnetwork.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Size(max = 60)
    @NotNull
    @Column(name = "Login", nullable = false, length = 60)
    private String login;

    @Size(max = 60)
    @NotNull
    @Column(name = "Password", nullable = false, length = 60)
    private String password;


    @Size(max = 45)
    @NotNull
    @Column(name = "Name", nullable = false, length = 45)
    private String username;

}