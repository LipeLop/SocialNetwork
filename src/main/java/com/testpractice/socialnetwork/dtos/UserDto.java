package com.testpractice.socialnetwork.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;


@Value
@EqualsAndHashCode
public class UserDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 45)
    String nickname;
}