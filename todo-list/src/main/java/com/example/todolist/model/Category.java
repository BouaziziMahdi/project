package com.example.todolist.model;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
public class Category {
    private Integer id;
    private String name;



    public Category() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category(String name) {

        this.name = name;
    }

}



