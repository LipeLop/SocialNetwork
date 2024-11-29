package com.testpractice.socialnetwork;

import com.testpractice.socialnetwork.reps.FriendShipRep;
import com.testpractice.socialnetwork.reps.UserRep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class FriendShipServiceTest {

    @Autowired
    private FriendShipRep friendShipRep;

    @Autowired
    private UserRep userRep;

//    @Test
//    public void testAddFriends() {
//        User user = new User();
//        user.setLogin("123");
//        user.setPassword("123");
//        User friend = new User();
//        friend.setLogin("2123");
//        friend.setPassword("2123");
//
//        userRep.save(user);
//        userRep.save(friend);
//
//        user = userRep.findByLogin(user.getLogin());
//        friend = userRep.findByLogin(friend.getLogin());
//
//        // Создаем FriendshipId
//        FriendshipId friendshipId = new FriendshipId();
//        friendshipId.setUserFirst(user.getId()); // Предполагается, что у вас есть метод getId()
//        friendshipId.setUserSecond(friend.getId());
//
//        // Устанавливаем FriendshipId в Friendship
//        Friendship friendship1 = new Friendship();
//        friendship1.setId(friendshipId);
//        friendship1.setUserFirst(user);
//        friendship1.setUserSecond(friend);
//
//        friendShipRep.save(friendship1);
//
//        Friendship friendship2 = friendShipRep.findFirstByUserFirst(user);
//        assertEquals(friendship1, friendship2);
//    }

}
