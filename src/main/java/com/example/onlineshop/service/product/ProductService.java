package com.example.onlineshop.service.product;

import com.example.onlineshop.dto.product.CategoryDTO;
import com.example.onlineshop.entity.product.Category;
import com.example.onlineshop.mapper.CustomerMapper;
import com.example.onlineshop.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CustomerMapper customerMapper;

    public List<CategoryDTO> listAllCategories(){
        List<Category> category = productRepository.findAll();
        System.out.println(category.get(0));
        List<CategoryDTO> categoryDTO = customerMapper.categoryToCategoryDTO(category);
        return categoryDTO;
    }


}
