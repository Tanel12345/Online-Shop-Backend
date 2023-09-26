package com.example.onlineshop.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class CustomerDTO {

    private Integer id;

    @NotBlank(message = "First name must be expressed")
    private String firstName;

    @NotBlank(message = "Last name must be expressed")
    private String lastName;

    @NotBlank(message = "Username must be expressed")
    private String username;

    @NotBlank(message = "Email must be expressed")
    private String email;

    @NotNull(message = "Password must be expressed")
    private String hashcode;
}
