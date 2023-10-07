package com.example.onlineshop.mapper;

import com.example.onlineshop.dto.UserDto;
import com.example.onlineshop.dto.CustomerResponseDTO;
import com.example.onlineshop.dto.product.CategoryDTO;
import com.example.onlineshop.dto.product.ProductDTO;
import com.example.onlineshop.entity.product.Product;
import com.example.onlineshop.entity.user.UserEntity;
import com.example.onlineshop.entity.product.Category;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class Mappings {

    private ModelMapper modelMapper;

    public CustomerResponseDTO toResponseDTO(UserEntity user) {
        return modelMapper.map(user, CustomerResponseDTO.class);
    }

    public UserEntity toEntity(UserDto userDTO) {
        return modelMapper.map(userDTO, UserEntity.class);
    }

    public CustomerResponseDTO userEntityToResponseDto(UserEntity userEntity){
        return modelMapper.map(userEntity, CustomerResponseDTO.class);
    }

    public List<CategoryDTO> toCategoryDTOList(List<Category> categoryList) {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }
    public Category toCategory(CategoryDTO categoryDTO){
        return modelMapper.map(categoryDTO, Category.class);
    }

    public CategoryDTO toCategoryDTO(Category category){
        return modelMapper.map(category, CategoryDTO.class);
    }

    public Product toProduct(ProductDTO productDTO){
        return modelMapper.map(productDTO, Product.class);
    }

    public ProductDTO toProductDTO(Product product){
        return modelMapper.map(product, ProductDTO.class);
    }
}

