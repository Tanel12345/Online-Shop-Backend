package com.example.onlineshop.enums.fieldNames;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FieldName {
    USER_DTO_EMAIL("email"),
    USER_DTO_USERNAME("username"),
    PRODUCT_NAME("name"),
    CATEGORY_NAME("name");

    private final String fieldName;

}
