package com.example.onlineshop.controller;

import com.example.onlineshop.dto.product.CategoryDTO;
import com.example.onlineshop.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class ProductController {

    ProductService productService;

    @GetMapping()
//    public String listAllCategories() {
//        return "categories are listed";
    public List<CategoryDTO> listAllCategories() {
        return productService.listAllCategories();
    }
}
