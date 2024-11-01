package com.testpractice.socialnetwork.reps;

import com.testpractice.socialnetwork.entities.Friendship;
import com.testpractice.socialnetwork.entities.FriendshipId;
import com.testpractice.socialnetwork.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendShipRep extends JpaRepository<Friendship, FriendshipId> {

    Friendship findFirstByUserFirst(User user);


}
