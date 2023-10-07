package com.example.onlineshop.validation.validators;

import com.example.onlineshop.repository.CategoryRepository;
import com.example.onlineshop.validation.annotations.ValidCategoryId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidCategoryIdValidator implements ConstraintValidator<ValidCategoryId, Integer> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void initialize(ValidCategoryId constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer categoryId, ConstraintValidatorContext context) {

        if(categoryId == null) {
            return true; // Null values should be validated using @NotNull
        }

        if (!categoryRepository.existsById(categoryId)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Category Id '" + categoryId + "' doesn't exist")
                    .addConstraintViolation();
            return false;
        }
        return true;

//        if (categoryId == null) {
//            return true; // null checks should be handled with @NotNull
//        }
//        return categoryRepository.existsById(categoryId);
    }
}