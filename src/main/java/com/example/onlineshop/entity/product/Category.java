package com.example.onlineshop.entity.product;

<<<<<<< HEAD

import jakarta.persistence.*;

=======
>>>>>>> 63435f03e6be485d126f1fa31a504b215756c734
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
<<<<<<< HEAD

=======
>>>>>>> 63435f03e6be485d126f1fa31a504b215756c734
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

<<<<<<< HEAD



@Getter
@Setter
@Data

=======
@Getter
@Setter
@Data
>>>>>>> 63435f03e6be485d126f1fa31a504b215756c734
@Accessors(chain = true)
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

<<<<<<< HEAD

    private String CategoryName;



    private String name;

=======
    private String name;
>>>>>>> 63435f03e6be485d126f1fa31a504b215756c734
}
