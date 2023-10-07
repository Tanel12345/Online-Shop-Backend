package com.example.onlineshop.dto.product;

import com.example.onlineshop.config.CustomBigDecimalDeserializer;
import com.example.onlineshop.config.CustomIntegerDeserializer;
import com.example.onlineshop.entity.product.Category;
import com.example.onlineshop.validation.annotations.UniqueProductName;
import com.example.onlineshop.validation.annotations.ValidCategoryId;
import com.example.onlineshop.validation.groups.ValidationSteps;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.UniqueElements;

import java.math.BigDecimal;

@Data
@Accessors(chain = true)

public class ProductDTO {

    //TODO: @ValiCategoryID muuta universaalseks

    private Integer id;

    @NotBlank(message = "Product name must be expressed")
//    @UniqueProductName //TODO:probleem, et otsib kõigist kategooriatest
    private String name;


    @JsonDeserialize(using = CustomBigDecimalDeserializer.class)
//    @NotNull(message = "Product price must be expressed")
    @NotNull(message = "Product price must be a valid number")
    @DecimalMin(value = "0.00", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @NotBlank(message = "Product description must be expressed")
    private String description;

    @JsonDeserialize(using = CustomIntegerDeserializer.class)
    @NotNull(message = "Category ID must be a valid number")
    @ValidCategoryId
//    @NotNull(message = "Category ID must be expressed")
    private Integer categoryId;
}
