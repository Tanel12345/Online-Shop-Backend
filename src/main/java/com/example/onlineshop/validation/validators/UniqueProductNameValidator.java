package com.example.onlineshop.validation.validators;

import com.example.onlineshop.dto.product.ProductDTO;
import com.example.onlineshop.repository.ProductRepository;
import com.example.onlineshop.validation.annotations.UniqueProductName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueProductNameValidator implements ConstraintValidator<UniqueProductName, ProductDTO> {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void initialize(UniqueProductName constraintAnnotation) {
    }

    @Override
    public boolean isValid(ProductDTO productDTO, ConstraintValidatorContext context) {

    if (productDTO.getName() == null || productDTO.getCategoryId() == null) {
            return true; // or false based on your requirement
        }

        if (productRepository.existsByIdAndName(productDTO.getCategoryId(), productDTO.getName())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Product name '" + productDTO.getName() + "' already exists")
                    .addPropertyNode("name")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
