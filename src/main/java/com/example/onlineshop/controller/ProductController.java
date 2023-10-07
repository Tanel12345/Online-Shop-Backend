package com.example.onlineshop.controller;

import com.example.onlineshop.dto.product.CategoryDTO;
import com.example.onlineshop.entity.product.Product;
import com.example.onlineshop.service.product.CategoryService;
import com.example.onlineshop.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping()
public class ProductController {

    private CategoryService categoryService;
    private ProductService productService;

    @GetMapping("/category")
    public List<CategoryDTO> listAllCategories() {
        return categoryService.listAllCategories();
    }

    @GetMapping("/product/category/{categoryId}")
    public ResponseEntity<List<Product>> getAllProductsByCategoryId(@PathVariable Integer categoryId) {
        List<Product> products = productService.findAllProductsByCategoryId(categoryId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
