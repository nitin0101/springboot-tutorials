package com.nitin.springboot_todo_app.repository;

import com.nitin.springboot_todo_app.model.Configurations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfigurationsRepository extends JpaRepository<Configurations, Long> {
    // Spring Data JPA provides default CRUD methods
}
