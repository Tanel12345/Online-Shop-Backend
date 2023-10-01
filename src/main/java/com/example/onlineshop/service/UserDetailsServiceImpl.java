package com.example.onlineshop.service;


import com.example.onlineshop.config.CustomUserDetails;
import com.example.onlineshop.entity.User;
import com.example.onlineshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public User findUserEntityByUsername(String username) {
        return userRepository.findByEmail(username);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        // You can add additional roles as needed
//        if (user.isAdmin()) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        }

        return new CustomUserDetails(user);
    }
}
