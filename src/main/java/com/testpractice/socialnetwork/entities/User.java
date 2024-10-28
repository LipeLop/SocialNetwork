package com.testpractice.socialnetwork.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Integer id;

    @Size(max = 60)
    @NotNull
    @Column(name = "Login", nullable = false, length = 60)
    private String login;

    @Size(max = 60)
    @NotNull
    @Column(name = "Password", nullable = false, length = 60)
    private String password;

}