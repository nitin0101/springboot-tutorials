package com.nitin.springboot_todo_app.controller;

import com.nitin.springboot_todo_app.model.Todo;
import com.nitin.springboot_todo_app.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;
    @GetMapping
    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo){
        return  todoRepository.save(todo);
    }

    @PutMapping("/{id}")
    public  Todo updateTodo(@PathVariable Long id,
                            @RequestBody Todo todoDetails){
        Todo todo = todoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Todo not found"));

        todo.setCompleted(todoDetails.isCompleted());
        todo.setTitle(todoDetails.getTitle());

        return todoRepository.save(todo);
    }

    @GetMapping("/{id}")
    public Optional<Todo> getTodoById(@PathVariable Long id){
        return todoRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id){
        todoRepository.deleteById(id);
    }


}
