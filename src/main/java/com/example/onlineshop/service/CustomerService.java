package com.example.onlineshop.service;

import com.example.onlineshop.dto.CustomerDTO;
import com.example.onlineshop.dto.CustomerResponseDTO;
import com.example.onlineshop.mapper.CustomerMapper;
import com.example.onlineshop.model.Customer;
import com.example.onlineshop.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    private final CustomerMapper customerMapper;

    public CustomerResponseDTO createCustomer(CustomerDTO customerDTO) {

        Customer customer = customerMapper.toEntity(customerDTO);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setHashcode(passwordEncoder.encode(customerDTO.getHashcode()));

        Customer savedCustomer = customerRepository.save(customer);

        return customerMapper.toResponseDTO(savedCustomer);
    }
}

