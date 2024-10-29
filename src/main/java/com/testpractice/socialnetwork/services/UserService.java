package com.testpractice.socialnetwork.services;

import com.testpractice.socialnetwork.entities.User;
import com.testpractice.socialnetwork.exceptions.UserIsAlreadyExist;
import com.testpractice.socialnetwork.reps.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRep userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetailsImpl(user);
    }

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
    public List<User> findAllByNameExceptMe(String Search_login, String myLogin) {
        return userRepository.findAllByLoginContainsAndLoginNot(Search_login, myLogin);
    }
}
