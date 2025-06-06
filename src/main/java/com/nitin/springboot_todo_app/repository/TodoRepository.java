package com.nitin.springboot_todo_app.repository;

import com.nitin.springboot_todo_app.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    // Spring Data JPA provides default CRUD methods
}