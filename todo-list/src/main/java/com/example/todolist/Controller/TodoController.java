package com.example.todolist.Controller;

import com.example.todolist.dto.TodoDto;
import com.example.todolist.service.TodoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/todo")
@CrossOrigin(origins = "*")
@Tag(name = "Todo", description = "Todo API")
public class TodoController {
    private final TodoService todoService;


    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TodoDto> create(@RequestBody TodoDto todoDto) {
        TodoDto createdTodoDto = todoService.create(todoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodoDto);
    }
      @GetMapping(path="/all" )
        public ResponseEntity<List<TodoDto>> getAll() {
            List<TodoDto> todoDtoList = todoService.getAll();
            return ResponseEntity.status(HttpStatus.OK).body(todoDtoList);
        }
        @PutMapping(path="/update" )
        public ResponseEntity<TodoDto> update(@RequestBody TodoDto todoDto) {
            TodoDto updatedTodoDto = todoService.update(todoDto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedTodoDto);
        }
        @DeleteMapping(path="/delete/{idTodo}" )
        public ResponseEntity<?> delete(@PathVariable("idTodo") int id) {
            todoService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        @GetMapping(path="/find/{id}" )
        public ResponseEntity<TodoDto> getById(@PathVariable int id) {
            TodoDto todoDto = todoService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(todoDto);
        }
}
