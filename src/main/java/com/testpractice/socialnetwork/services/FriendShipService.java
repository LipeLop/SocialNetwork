package com.testpractice.socialnetwork.services;

import com.testpractice.socialnetwork.dtos.UserDto;
import com.testpractice.socialnetwork.entities.mysql.Friendship;
import com.testpractice.socialnetwork.entities.mysql.FriendshipId;
import com.testpractice.socialnetwork.entities.mysql.User;
import com.testpractice.socialnetwork.mappers.UserMapper;
import com.testpractice.socialnetwork.reps.FriendShipRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendShipService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FriendShipRep friendShipRep;

    public void addFriends(UserDto user_first, UserDto user_second) {
        User user1 = userMapper.fromUserDto(user_first);
        User user2 = userMapper.fromUserDto(user_second);
        Friendship friendship = new Friendship(user1, user2);
        FriendshipId friendshipId = new FriendshipId();
        friendship.setId(friendshipId);
        friendshipId.setUserFirst(user1.getId());
        friendshipId.setUserSecond(user2.getId());
        friendShipRep.save(friendship);
        Friendship friendship2 = new Friendship(user2, user1);
        FriendshipId friendshipId2 = new FriendshipId();
        friendship2.setId(friendshipId2);
        friendshipId2.setUserFirst(user2.getId());
        friendshipId2.setUserSecond(user1.getId());
        friendShipRep.save(friendship2);

    }

    public List<UserDto> getFriends(UserDto user) {
        List<User> friends = friendShipRep.findFriendsByUserId(user.getId());
        return userMapper.toUserDtos(friends);
    }
}
