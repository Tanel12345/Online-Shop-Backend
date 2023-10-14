package com.example.onlineshop.repository;

import com.example.onlineshop.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByName(String name);


    boolean existsByName(String name);

    boolean existsByIdAndName(Integer id, String name);
    // Custom query to filter products by category ID and price range
    @Query("SELECT p FROM Product p " +
            "WHERE p.category.id = :categoryId " +
            "AND p.price >= :minPrice " +
            "AND p.price <= :maxPrice")
    List<Product> findByCategoryIdAndPriceBetween(
            @Param("categoryId") Integer categoryId,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice
    );
    List<Product> findByCategoryId(@Param("categoryId") Integer categoryId);
    List<Product> findByPriceBetween(@Param("minPrice") Double minPrice, @Param("maxPrice") Double maxPrice);

}
