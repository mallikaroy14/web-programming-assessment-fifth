package com.example.JPATest.service;


import com.example.JPATest.exceptions.EntityNotFoundException;
import com.example.JPATest.model.Todo;
import com.example.JPATest.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServices {

    @Autowired
    TodoRepository todoRepository;


    public Todo create(Todo todo) {
        Todo newTask = todoRepository.save(todo);
        return newTask;
    }

    public List<Todo> get() {
        List<Todo> todoTask = todoRepository.findAll();
        return todoTask;
    }

    public Optional<Todo> getById(Long id){
       Optional<Todo> todo = todoRepository.findById(id);
        return todo;
    }

    public Todo updateTodo(Todo todo) {
        Optional<Todo> todoOptional = todoRepository.findById(todo.getId());
        if (todoOptional.isPresent()) {
            todoOptional.get().setDescription(todo.getDescription());
            Todo updatedProduct = todoRepository.save(todoOptional.get());
            return updatedProduct;
        } else {
            throw new EntityNotFoundException("Product with the given id is not exist. Please check Products Items");
        }
    }

    public String deleteById(Long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isPresent()) {
            todoRepository.deleteById(id);
            return "Todo List Deleted";
        } else {
            throw new EntityNotFoundException("Todo List with the given id is not exist. Please try again");
        }
    }
}
