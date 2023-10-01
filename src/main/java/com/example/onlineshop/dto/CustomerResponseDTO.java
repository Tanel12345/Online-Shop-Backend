package com.example.onlineshop.dto;

import com.example.onlineshop.enums.user.UserType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CustomerResponseDTO {
    private Integer id;

    private String firstName;

    private String lastName;

    private String username;

    private LocalDateTime createdAt;

    private String email;

    private UserType userType;
}
