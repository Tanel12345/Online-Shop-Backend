package com.example.onlineshop.mapper;

import com.example.onlineshop.dto.UserDto;
import com.example.onlineshop.dto.CustomerResponseDTO;
import com.example.onlineshop.entity.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerMapper {

    private ModelMapper modelMapper;

    public CustomerResponseDTO toResponseDTO(User user) {
        return modelMapper.map(user, CustomerResponseDTO.class);
    }

    public User toEntity(UserDto userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
    public CustomerResponseDTO userEntityToResponseDto(User userEntity){
        return modelMapper.map(userEntity, CustomerResponseDTO.class);
    }
}

