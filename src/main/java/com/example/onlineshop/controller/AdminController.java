package com.example.onlineshop.controller;

import com.example.onlineshop.dto.product.CategoryDTO;
import com.example.onlineshop.dto.product.ProductDTO;
import com.example.onlineshop.service.product.CategoryService;
import com.example.onlineshop.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    CategoryService categoryService;
    ProductService productService;

    @PostMapping("/createCategory")
    public CategoryDTO createProductCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        return categoryService.createCategory(categoryDTO);
    }

    @PostMapping("/createProduct")
    public ProductDTO createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }
}


