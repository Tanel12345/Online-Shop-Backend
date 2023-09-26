package com.example.onlineshop.controller;

import com.example.onlineshop.dto.CustomerDTO;
import com.example.onlineshop.dto.CustomerResponseDTO;
import com.example.onlineshop.service.CustomerService;
import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("/customers")
@CrossOrigin(origins = "http://localhost:4200/")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public CustomerResponseDTO createCustomer (@Valid @RequestBody CustomerDTO customerDTO) {
        System.out.println(customerDTO);
        return customerService.createCustomer(customerDTO);
    }
}
