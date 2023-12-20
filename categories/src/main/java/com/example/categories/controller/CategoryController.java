package com.example.categories.controller;

import com.example.categories.dto.CategoryDto;
import com.example.categories.service.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoryController {
    private final CategoryServiceImpl service;

    @PostMapping("/save")
    public ResponseEntity<Integer> save(
            @RequestBody CategoryDto categorydto
    ) {
        return ResponseEntity.ok(service.addCategory(categorydto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CategoryDto>> findCategoryByUsers(
            @PathVariable Integer userId
    ) {
        return ResponseEntity.ok(service.findCategoryByUsers(userId));

    }
    @GetMapping("/all/{id}")
    public ResponseEntity<CategoryDto> findById(
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(service.getCategoryById(id));
    }
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> findAll() {
        return ResponseEntity.ok(service.getAllCategories());
    }
    @PutMapping("/")
    public ResponseEntity<CategoryDto> updateCategory(
            @RequestBody CategoryDto categoryDto
    ) {
        return ResponseEntity.ok(service.updateCategory( categoryDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable Integer id
    ) {
        service.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}

