package com.example.onlineshop.repository;

import com.example.onlineshop.entity.product.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Category, Integer> {
}
