package com.example.onlineshop.service;

import com.example.onlineshop.dto.UserDto;
import com.example.onlineshop.dto.CustomerResponseDTO;
import com.example.onlineshop.enums.fieldNames.FieldName;
import com.example.onlineshop.enums.user.UserType;
import com.example.onlineshop.exception.runtimeExceptions.MyException;
import com.example.onlineshop.mapper.Mappings;
import com.example.onlineshop.entity.user.UserEntity;
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

    private final Mappings mappings;

    public CustomerResponseDTO createCustomer(UserDto userDTO) {

        //Lisatud on UserDTO klassile isetehtud @UniqueEmail ja @UniqueUsername, mis teevad sama valideerimise
//        if (userRepository.findByUsername(userDTO.getUsername()) != null) {
//            throw new MyException(
//                    "Username '" + userDTO.getUsername() +"' already exists", FieldName.USER_DTO_USERNAME.getFieldName()
//            );
//        }
//
//        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
//            throw new MyException(
//                    "Email '" + userDTO.getEmail() +"' already exists", FieldName.USER_DTO_EMAIL.getFieldName()
//            );
//        }

        UserEntity user = mappings.toEntity(userDTO);
        user.setCreatedAt(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUserType(UserType.CUSTOMER);

        UserEntity savedUser = userRepository.save(user);

        return mappings.toResponseDTO(savedUser);
    }
}

