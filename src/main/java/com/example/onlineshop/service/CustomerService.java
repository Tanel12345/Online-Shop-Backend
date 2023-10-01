package com.example.onlineshop.service;

import com.example.onlineshop.dto.UserDto;
import com.example.onlineshop.dto.CustomerResponseDTO;
import com.example.onlineshop.mapper.CustomerMapper;
import com.example.onlineshop.entity.User;
import com.example.onlineshop.repository.UserRepository;
import lombok.AllArgsConstructor;
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

        User user = customerMapper.toEntity(userDTO);
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User savedUser = userRepository.save(user);

        return customerMapper.toResponseDTO(savedUser);
    }
}

