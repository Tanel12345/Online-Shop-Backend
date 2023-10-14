package com.example.onlineshop.service;

import com.example.onlineshop.entity.user.UserEntity;
import com.example.onlineshop.enums.user.UserType;
import com.example.onlineshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.User;
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

    public UserEntity findUserEntityByUsername(String username) { System.out.println(username);

        return userRepository.findByUsername(username);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        if (user.getUserType() == UserType.CUSTOMER) {
            authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        } else if (user.getUserType() == UserType.ADMIN) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
