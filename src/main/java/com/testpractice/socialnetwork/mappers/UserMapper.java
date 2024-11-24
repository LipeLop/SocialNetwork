package com.testpractice.socialnetwork.mappers;

import com.testpractice.socialnetwork.dtos.UserDto;
import com.testpractice.socialnetwork.entities.User;
import com.testpractice.socialnetwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    private UserService userService;


    public UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getNickname());
    }
    public List<UserDto> toUserDtos(List<User> users) {
        return users.stream().map(this::toUserDto).collect(Collectors.toList());
    }
    public User fromUserDto(UserDto userDto) {
        return userService.findById(userDto.getId());
    }
}
