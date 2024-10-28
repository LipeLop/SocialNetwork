package com.testpractice.socialnetwork.reps;

import com.testpractice.socialnetwork.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRep extends JpaRepository<User, Integer> {

    User findByLogin(String login);
}
