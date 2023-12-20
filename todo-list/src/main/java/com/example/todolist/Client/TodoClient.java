package com.example.todolist.Client;

import com.example.todolist.model.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name ="category",url = "http://localhost:8222/api/v1/category")
public interface TodoClient {
    @GetMapping(path="/all" )
     List<Category> getAllCategory();
    @GetMapping(path="/{id}" )
    Category getCategoryById(@PathVariable int id);
}
