package com.example.onlineshop.validation.validators;

import com.example.onlineshop.repository.UserRepository;
import com.example.onlineshop.validation.annotations.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    UserRepository userRepository;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (userRepository.existsByUsername(username)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Username '" + username + "' already exists")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
