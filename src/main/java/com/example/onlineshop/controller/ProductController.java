package com.example.onlineshop.controller;

import com.example.onlineshop.dto.product.CategoryDTO;
import com.example.onlineshop.service.product.ProductService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/category")
public class ProductController {

    ProductService productService;
    @GetMapping("")
    public List<CategoryDTO> listAllCategories() {
        return productService.listAllCategories();
    }
}
