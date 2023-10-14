package com.example.onlineshop.service.product;

import com.example.onlineshop.dto.product.ProductDTO;
import com.example.onlineshop.entity.product.Product;
import com.example.onlineshop.enums.fieldNames.FieldName;
import com.example.onlineshop.exception.runtimeExceptions.MyException;
import com.example.onlineshop.mapper.Mappings;
import com.example.onlineshop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private ProductRepository productRepository;
    private Mappings mappings;

    public ProductDTO createProduct(ProductDTO productDTO) {

        Product product = mappings.toProduct(productDTO);

//        if (productRepository.findByName(product.getName()) != null) {
//            throw new MyException(
//                    "Product name' " + product.getName() + "' already exists",
//                    FieldName.PRODUCT_NAME.getFieldName());
//        }

        productRepository.save(product);

        return mappings.toProductDTO(product);
    }

    public List<Product> findAllProductsByCategoryId(Integer categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public List<Product> filterProducts(Integer categoryId, Double minPrice, Double maxPrice) {
        if (categoryId != null && minPrice != null && maxPrice != null) {
            return productRepository.findByCategoryIdAndPriceBetween(categoryId, minPrice, maxPrice);
        } else if (categoryId != null) {
            return productRepository.findByCategoryId(categoryId);
        } else if (minPrice != null && maxPrice != null) {
            return productRepository.findByPriceBetween(minPrice, maxPrice);
        } else {
            return productRepository.findAll();
        }
    }
}
