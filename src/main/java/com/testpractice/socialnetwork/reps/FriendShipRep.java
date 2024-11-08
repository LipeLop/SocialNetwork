package com.testpractice.socialnetwork.reps;

import com.testpractice.socialnetwork.entities.Friendship;
import com.testpractice.socialnetwork.entities.FriendshipId;
import com.testpractice.socialnetwork.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendShipRep extends JpaRepository<Friendship, FriendshipId> {

    Friendship findFirstByUserFirst(User user);

    @Query("SELECT f.userSecond FROM Friendship f WHERE f.userFirst.id = :userId")
    List<User> findFriendsByUserId(@Param("userId") Integer userId);

}
