package com.example.onlineshop.dto.product;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CategoryDTO {

    private Integer id;

    @NotBlank(message = "Category name must be expressed")
    private String name;
}
