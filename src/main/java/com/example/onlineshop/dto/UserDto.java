package com.example.onlineshop.dto;

import com.example.onlineshop.enums.user.UserType;
import com.example.onlineshop.validation.annotations.UniqueEmail;
import com.example.onlineshop.validation.annotations.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;


@Data
@Accessors(chain = true)
public class UserDto {

    private Integer id;

    @NotBlank(message = "First name must be expressed")
    private String firstName;

    @NotBlank(message = "Last name must be expressed")
    private String lastName;

    @NotBlank(message = "Username must be expressed")
    @UniqueUsername
    private String username;

    @NotBlank(message = "Email must be expressed")
    @UniqueEmail
    private String email;

    @NotBlank(message = "Password must be expressed")
    private String password;

    private UserType userType;
    private LocalDateTime createdAt;
}
