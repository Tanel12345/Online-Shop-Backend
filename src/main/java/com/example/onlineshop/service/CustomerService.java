package com.example.onlineshop.service;

import com.example.onlineshop.dto.UserDto;
import com.example.onlineshop.dto.CustomerResponseDTO;
import com.example.onlineshop.enums.user.UserType;
import com.example.onlineshop.exception.EmailAlreadyExists;
import com.example.onlineshop.exception.UsernameAlreadyExists;
import com.example.onlineshop.mapper.CustomerMapper;
import com.example.onlineshop.entity.UserEntity;
import com.example.onlineshop.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CustomerService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final CustomerMapper customerMapper;

    public CustomerResponseDTO createCustomer(UserDto userDTO) {

        if (!(userRepository.findByUsername(userDTO.getUsername()) == null)) {
            throw new UsernameAlreadyExists("Username '" + userDTO.getUsername() +"' already exists");
        }

        if (!(userRepository.findByEmail(userDTO.getUsername()) == null)) {
            throw new EmailAlreadyExists("Email '" + userDTO.getEmail() +"' already exists");
        }

        UserEntity user = customerMapper.toEntity(userDTO);
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUserType(UserType.CUSTOMER);

        UserEntity savedUser = userRepository.save(user);

        return customerMapper.toResponseDTO(savedUser);
    }
}

