package com.example.todolist.service;


import com.example.todolist.Client.TodoClient;
import com.example.todolist.dto.TodoDto;
import com.example.todolist.model.Category;
import com.example.todolist.model.Todo;
import com.example.todolist.repository.TodoRepository;

import com.example.todolist.valitador.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoClient todoClient;


    public TodoDto create(TodoDto todoDto) {

        Todo todo = TodoDto.toEntity(todoDto);
        List<Category> categories = todoClient.getAllCategory();
        Category selectedCategory = null;

        for (Category category : categories) {
            System.out.println("Checking category: " + category.getId());
            int categoryId = todoDto.getCategoryId();

            if (Objects.equals(category.getId(), categoryId)) {
                System.out.println("Match found for categoryId: " + categoryId);
                selectedCategory = category; // Set the selected category
                todoDto.setCategoryId(category.getId());
                break;
            }

        }

        if (selectedCategory != null) {
            // Assuming todoDto has a setter for the category property
            todoDto.setCategory((Category) Collections.singletonList(selectedCategory));

        }

        TodoDto createdTodo = TodoDto.FromEntity(todoRepository.save(TodoDto.toEntity(todoDto)));
        return TodoDto.FromEntity(todo);
    }

    public List<TodoDto> getAll() {

        List<Todo> todoList = todoRepository.findAll();
        List<TodoDto> todoDtoList = new ArrayList<>();
        for (Todo todo : todoList) {
            TodoDto todoDto = TodoDto.FromEntity(todo);
            todoDtoList.add(todoDto);
        }
        return todoDtoList;
    }
    public TodoDto findById(int id){
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        return TodoDto.FromEntity(todo);
    }
    public TodoDto update(TodoDto todoDto) {

        Todo todo = TodoDto.toEntity(todoDto);
        TodoDto createdTodo = TodoDto.FromEntity(todoRepository.save(TodoDto.toEntity(todoDto)));
        return TodoDto.FromEntity(todo);
    }
    public TodoDto delete(int id) {
        TodoDto todo = TodoDto.FromEntity(todoRepository.findById(id).orElseThrow());
        todoRepository.deleteById(id);
        return todo;

    }
}

