package com.example.onlineshop.mapper;

import com.example.onlineshop.dto.CustomerDTO;
import com.example.onlineshop.dto.CustomerResponseDTO;
import com.example.onlineshop.model.Customer;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerMapper {

    private ModelMapper modelMapper;

    public CustomerResponseDTO toResponseDTO(Customer customer) {
        return modelMapper.map(customer, CustomerResponseDTO.class);
    }

    public Customer toEntity(CustomerDTO customerDTO) {
        return modelMapper.map(customerDTO, Customer.class);
    }
}
