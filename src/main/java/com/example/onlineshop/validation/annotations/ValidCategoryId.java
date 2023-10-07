package com.example.onlineshop.validation.annotations;

import com.example.onlineshop.validation.validators.ValidCategoryIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidCategoryIdValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCategoryId {
    String message() default "Category Id doesn't exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
