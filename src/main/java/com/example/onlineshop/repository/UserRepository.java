package com.example.onlineshop.repository;

import com.example.onlineshop.entity.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername (String username);
}
