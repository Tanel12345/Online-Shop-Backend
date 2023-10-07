package com.example.onlineshop.repository;

import com.example.onlineshop.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);
    List<Product> findByCategoryId(Integer categoryId);

    boolean existsByName(String name);

    boolean existsByIdAndName(Integer id, String name);
}
