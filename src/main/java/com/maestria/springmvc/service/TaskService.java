package com.maestria.springmvc.service;

import com.maestria.springmvc.model.Task;
import java.util.List;
import java.util.Map;

public interface TaskService {

    List<Task> findAll();

    Task findById(Long id);

    Task save(Task task);

    Task update(Long id, Task task);

    Task partialUpdate(Long id, Map<String, Object> updates);

    void delete(Long id);
}
