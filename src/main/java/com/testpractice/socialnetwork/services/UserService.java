package com.testpractice.socialnetwork.services;


import com.testpractice.socialnetwork.entities.mysql.User;
import com.testpractice.socialnetwork.exceptions.UserIsAlreadyExist;
import com.testpractice.socialnetwork.reps.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


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
    @Cacheable(value = "users", key = "#id")
    public User findById(int id) {
        return userRepository.findById(id);
    }


}
