package com.example.onlineshop.validation.annotations;
import com.example.onlineshop.validation.validators.UniqueUsernameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueUsernameValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {
    String message() default "Username is already taken";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
