package com.testpractice.socialnetwork.services;

import com.testpractice.socialnetwork.entities.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_USER");
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Логика проверки срока действия учетной записи
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Логика проверки блокировки учетной записи
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Логика проверки срока действия учетных данных
    }

    @Override
    public boolean isEnabled() {
        return true; // Логика проверки активности учетной записи
    }
}

