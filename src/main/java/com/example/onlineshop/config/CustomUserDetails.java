package com.example.onlineshop.config;


import com.example.onlineshop.entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class CustomUserDetails implements UserDetails {

    private final UserEntity user;

    public CustomUserDetails(UserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // implementation depending on your user entity structure
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // implementation depending on your user entity structure
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // implementation depending on your user entity structure
        return true;
    }

    @Override
    public boolean isEnabled() {
        // implementation depending on your user entity structure
        return true;
    }
}
