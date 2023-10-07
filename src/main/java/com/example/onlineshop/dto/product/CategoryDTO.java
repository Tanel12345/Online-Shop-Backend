package com.example.onlineshop.dto.product;


import lombok.Data;
import lombok.experimental.Accessors;


    @Data
    @Accessors(chain = true)
    public class CategoryDTO {

        private Integer id;

        private String categoryName;
    }



