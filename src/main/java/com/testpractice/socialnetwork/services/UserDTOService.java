package com.testpractice.socialnetwork.services;

import com.testpractice.socialnetwork.dtos.UserDto;
import com.testpractice.socialnetwork.entities.User;
import com.testpractice.socialnetwork.mappers.UserMapper;
import com.testpractice.socialnetwork.reps.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDTOService {

    @Autowired
    private UserRep userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDto> findUsersNotInFriendsListAndNotMe(List<UserDto> friendsList) {
        List<Integer> ids = friendsList.stream().map(UserDto::getId).toList();
        List<User> result = userRepository.findAllByIdNotIn(ids);
        return userMapper.toUserDtos(result);

    }
    public List<UserDto> findAllByNameExceptMeandFriends(String username, List<UserDto> friendsAndMe) {
        List<Integer> ids = friendsAndMe.stream().map(UserDto::getId).toList();
        List<User> result = userRepository.findAllByNicknameContainsAndIdNotIn(username, ids);
        return userMapper.toUserDtos(result);
    }
    public UserDto findById(Integer userId) {
        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        return userMapper.toUserDto(user);
    }
}
