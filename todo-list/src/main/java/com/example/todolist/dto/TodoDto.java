package com.example.todolist.dto;

import com.example.todolist.model.Category;
import com.example.todolist.model.Todo;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoDto{
    private  int id;
    @NotEmpty(message = "Title is required")
    @NotNull(message = "Title is required")
    private String title;
    private boolean done;
    @NotEmpty(message = "description is required")
    @NotNull(message = "description is required")
    private String description;
    @NotEmpty(message = "date is required")
    @NotNull(message = "date is required")
    private String date;
    @JsonProperty("category")
    private Category category;
     private int categoryId;
        public static Todo  toEntity(TodoDto todoDto) {
            return Todo.builder()
                    .id(todoDto.getId())
                    .title(todoDto.getTitle())
                    .done(todoDto.isDone())
                    .description(todoDto.getDescription())
                    .date(todoDto.getDate())
                    .category(todoDto.getCategory())
                    .categoryId(todoDto.getCategoryId())
                    .build();
        }

    public static TodoDto FromEntity(Todo todo) {
        return TodoDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .done(todo.isDone())
                .description(todo.getDescription())
                .date(todo.getDate())
                .category(todo.getCategory())
                .categoryId(todo.getCategoryId())
                .build();
    }



}
