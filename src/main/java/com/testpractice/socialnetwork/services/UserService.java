package com.testpractice.socialnetwork.services;

import com.testpractice.socialnetwork.entities.User;
import com.testpractice.socialnetwork.exceptions.UserIsAlreadyExist;
import com.testpractice.socialnetwork.reps.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRep userRepository;


    public void register(User user) throws UserIsAlreadyExist {
        User user1 = findByLogin(user.getLogin());
        if (user1 != null) {
            throw new UserIsAlreadyExist("Пользователь с таким логином уже существует");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }
    public List<User> findAllExceptMe(String login) {
        return userRepository.findAllByLoginNot(login);
    }
    public List<User> findAllByNameExceptMeandFriends(String username, List<User> friendsAndMe) {
        List<String> logins = friendsAndMe.stream().map(User::getLogin).toList();
        return userRepository.findAllByNicknameContainsAndLoginNotIn(username, logins);
    }
    public User findById(int id) {
        return userRepository.findById(id);
    }
    public List<User> findUsersNotInFriendsListAndNotMe(List<User> friendsList) {
        List<String> logins = friendsList.stream().map(User::getLogin).toList();
        return userRepository.findAllByLoginNotIn(logins);

    }

    public User getUserById(int receiverId) {
        return userRepository.findById(receiverId);
    }
}
