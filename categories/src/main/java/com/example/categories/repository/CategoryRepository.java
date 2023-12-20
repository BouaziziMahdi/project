package com.example.categories.repository;

import com.example.categories.dto.CategoryDto;
import com.example.categories.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
