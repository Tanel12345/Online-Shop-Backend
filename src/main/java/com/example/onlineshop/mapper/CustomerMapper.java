package com.example.onlineshop.mapper;

import com.example.onlineshop.dto.UserDto;
import com.example.onlineshop.dto.CustomerResponseDTO;
import com.example.onlineshop.dto.product.CategoryDTO;
import com.example.onlineshop.entity.UserEntity;
import com.example.onlineshop.entity.product.Category;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CustomerMapper {

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

    public List<CategoryDTO> categoryToCategoryDTO(List<Category> categoryList) {
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }

//    public List<CategoryDTO> categoryToCategoryDTO(List<Category> categoryList) {
////        List<CategoryDTO> categoryDTOList= new ArrayList<>();
//        return modelMapper.map(categoryList, List<CategoryDTO>.class);
//    }
}

