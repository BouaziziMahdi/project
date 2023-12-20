package com.example.todolist.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Todo {
    @Id
    @GeneratedValue()
    private  int id;
    private String title;
    private boolean done;
    private String description;
    private String date;
    @Transient
    private Category category;
    private int categoryId;
    public void setCategory(Category category) {
        this.category =  category;
    }
}
