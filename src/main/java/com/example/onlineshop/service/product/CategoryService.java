package com.example.onlineshop.service.product;

import com.example.onlineshop.dto.product.CategoryDTO;
import com.example.onlineshop.entity.product.Category;
import com.example.onlineshop.enums.fieldNames.FieldName;
import com.example.onlineshop.exception.runtimeExceptions.MyException;
import com.example.onlineshop.mapper.Mappings;
import com.example.onlineshop.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final Mappings mappings;

    public List<CategoryDTO> listAllCategories() {
        List<Category> category = categoryRepository.findAll();
        return mappings.toCategoryDTOList(category);
    }

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {

        Category category = mappings.toCategory(categoryDTO);

        if (categoryRepository.findByName(category.getName()) != null) {
            throw new MyException(
                    "Category name' " + category.getName() +"' already exists",
                    FieldName.CATEGORY_NAME.getFieldName()
            );
        }

        categoryRepository.save(category);

        return mappings.toCategoryDTO(category);
    }
}
