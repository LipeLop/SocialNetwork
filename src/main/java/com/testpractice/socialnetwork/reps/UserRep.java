package com.testpractice.socialnetwork.reps;

import com.testpractice.socialnetwork.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRep extends JpaRepository<User, Integer> {

    User findByLogin(String login);
    List<User> findAllByLoginNot(String login);
    List<User> findAllByNicknameContainsAndIdNotIn(String username, List<Integer> ids);
    User findById(int id);
    List<User> findAllByLoginNotIn(List<String> logins);
    List<User> findAllByIdNotIn(List<Integer> ids);
}
