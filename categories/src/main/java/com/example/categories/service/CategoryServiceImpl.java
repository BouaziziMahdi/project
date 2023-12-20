package com.example.categories.service;

import com.example.categories.client.UserClient;
import com.example.categories.dto.CategoryDto;
import com.example.categories.repository.CategoryRepository;
import com.example.categories.valitador.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl {
    private final CategoryRepository categoryRepository;
    private final ObjectsValidator<CategoryDto> validator ;
  private  final UserClient userClient;
    public Integer addCategory(CategoryDto categorydto) {
        validator.validate(categorydto);

        return categoryRepository.save(CategoryDto.toEntity(categorydto)).getId();

    }

    public List<CategoryDto> findCategoryByUsers(Integer userId)
    {
        return categoryRepository.findAll().stream()
                .filter(category -> category.getUserId().equals(userId))
                .map(CategoryDto::fromEntity)
                .toList();
    }

    public CategoryDto updateCategory(CategoryDto categoryDto) {
        validator.validate(categoryDto);
        var category = categoryRepository.findById(categoryDto.getId()).orElseThrow();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setUserId(categoryDto.getUserId());
        return CategoryDto.fromEntity(categoryRepository.save(category));
    }

    public void deleteCategory(@PathVariable Integer id) {
        categoryRepository.deleteById(id);
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map( CategoryDto::fromEntity )
                .collect( Collectors.toList() );

    }

    public CategoryDto getCategoryById(Integer id) {
        return categoryRepository.findById(id).stream()
                .map( CategoryDto::fromEntity )
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("No category was found with the ID :" + id));
    }
}