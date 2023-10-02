package com.example.onlineshop.service;


import com.example.onlineshop.config.CustomUserDetails;
import com.example.onlineshop.entity.UserEntity;
import com.example.onlineshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**Overall, this class serves as a bridge between Spring Security and your application's user data. It loads user details
 *  from the database, associates roles/authorities, and provides the necessary information to Spring Security for authentication
 *  and authorization purposes. The CustomUserDetails class is likely used to store additional user-specific data,
 *  and it's not included in the code snippet you provided, but it's typically used to extend the basic user details provided by Spring Security.
 *
 *
 * The UserDetailsServiceImpl class is a service class that implements the UserDetailsService interface provided by Spring Security.
 * It's responsible for loading user-specific data for authentication and authorization purposes. Let's break down this class and explain
 * its key components:
 */
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    /**
     * findUserEntityByUsername(String username): This method retrieves a user entity by their email (assuming the email is used as the username)
     * . It queries the database through the userRepository and returns a User entity. If the user is not found, it returns null.
     * @param username
     * @return
     */

    public UserEntity findUserEntityByUsername(String username) {
        System.out.println(username);
        System.out.println("See"+userRepository.findByUsername(username));
        return userRepository.findByUsername(username);
    }

    /**
     * loadUserByUsername(String username): This method is part of the UserDetailsService interface and is used by Spring Security
     * to load user details for authentication. It takes a username as a parameter.
     *
     * It first tries to find a user in the database using the findByUsername method from the userRepository.
     * If the user is not found, a UsernameNotFoundException is thrown, indicating that the user was not found.
     * If the user is found, it creates a list of SimpleGrantedAuthority objects. In this example, it assigns the role
     * "ROLE_USER" to every user. You can customize this part to assign additional roles based on your application's requirements.
     * Finally, it creates and returns an instance of CustomUserDetails, a custom implementation of the UserDetails interface that
     * wraps the retrieved User entity. This custom implementation allows you to store additional user-specific information.
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername meetod "+username);
        UserEntity user = userRepository.findByUsername(username);
        System.out.println(user);
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
