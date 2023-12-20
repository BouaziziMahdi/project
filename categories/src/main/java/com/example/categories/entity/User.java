package com.example.categories.entity;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class User {
    private Integer id ;
    private String firstName ;
    private String lastName ;
    private String email ;
}
