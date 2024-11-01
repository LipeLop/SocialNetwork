package com.testpractice.socialnetwork.services;

import com.testpractice.socialnetwork.entities.Friendship;
import com.testpractice.socialnetwork.entities.User;
import com.testpractice.socialnetwork.reps.FriendShipRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendShipService {

    @Autowired
    private FriendShipRep friendShipRep;


    public void addFriends(User user1, User user2) {
        Friendship friendship = new Friendship(user1, user2);
        friendShipRep.save(friendship);
    }
}
