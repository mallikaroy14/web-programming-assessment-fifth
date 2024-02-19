package com.example.JPATest.contoller;

import com.example.JPATest.beans.ResponseHandler;
import com.example.JPATest.model.Todo;
import com.example.JPATest.service.TodoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    TodoServices todoServices;

    @GetMapping("/read")
    public List<Todo> getAll() {
        List<Todo> todolist = todoServices.get();
        return todolist;
    }

    @GetMapping("/read/{id}")
    public Optional<Todo> getById(@PathVariable Long id) {
        Optional<Todo> todo = todoServices.getById(id);
        return todo;
    }

    @PostMapping("/create")
    public Object create(@RequestBody Todo todo) {
        Todo todoOptional = todoServices.create(todo);
        if (todoOptional != null) {
            return ResponseHandler.createResponse("Product added", HttpStatus.CREATED, todoOptional);
        } else
            return ResponseHandler.createResponse("Product already exist ", HttpStatus.CONFLICT, null);
    }


    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody Todo todo) {
        Object updatedDescription = todoServices.updateTodo(todo);
        return ResponseHandler.createResponse("Customer name updated", HttpStatus.OK, updatedDescription);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        todoServices.deleteById(id);
        return ResponseHandler.createResponse("Deleted successfully", HttpStatus.OK, null);
    }
}

