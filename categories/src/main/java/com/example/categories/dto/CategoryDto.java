package com.example.categories.dto;


import com.example.categories.client.UserClient;
import com.example.categories.entity.Category;
import com.example.categories.entity.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CategoryDto {
    private Integer id;
    @NotNull(message = "name is required")
    @NotEmpty(message = "name is required")
    private String name;
    @NotNull(message = "description is required")
    private String description;
    private Integer UserId;
    private User user;

    public static  CategoryDto fromEntity(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .UserId(category.getUserId())
                .build();
    }
    public static Category toEntity(CategoryDto categoryDto){
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .userId(categoryDto.getUserId())
                .build();
    }
}
