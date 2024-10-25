package com.testpractice.socialnetwork;

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
    @Column(name = "idUser", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "Login", nullable = false, length = 45, unique = true)
    private String login;

    @Size(max = 45)
    @NotNull
    @Column(name = "Password", nullable = false, length = 45)
    private String password;

}