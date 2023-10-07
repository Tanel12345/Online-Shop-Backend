package com.example.onlineshop.dto.product;

<<<<<<< HEAD

import lombok.Data;
import lombok.experimental.Accessors;


    @Data
    @Accessors(chain = true)
    public class CategoryDTO {

        private Integer id;

        private String categoryName;
    }



=======
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CategoryDTO {

    private Integer id;

    @NotBlank(message = "Category name must be expressed")
    private String name;
}
>>>>>>> 63435f03e6be485d126f1fa31a504b215756c734
