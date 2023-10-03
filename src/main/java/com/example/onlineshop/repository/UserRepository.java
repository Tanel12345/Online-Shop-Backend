package com.example.onlineshop.repository;

import com.example.onlineshop.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);

    boolean existsByEmail(String email);
}
