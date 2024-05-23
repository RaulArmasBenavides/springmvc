package com.maestria.springmvc.repository;

import com.maestria.springmvc.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}