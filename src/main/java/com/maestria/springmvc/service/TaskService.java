package com.maestria.springmvc.service;

import com.maestria.springmvc.controller.exception.ResourceNotFoundException;
import com.maestria.springmvc.model.Task;
import com.maestria.springmvc.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task update(Long id, Task task) {
        Task existingTask = findById(id);
        existingTask.setTitle(task.getTitle());
        existingTask.setBody(task.getBody());
        existingTask.setStatus(task.getStatus());
        existingTask.setDatefinished(task.getDatefinished());

        return taskRepository.save(existingTask);
    }

    public Task partialUpdate(Long id, Map<String, Object> updates) {
        Task existingTask = findById(id);
        updates.forEach((key, value) -> {
            switch (key) {
                case "title":
                    existingTask.setTitle((String) value);
                    break;
                case "body":
                    existingTask.setBody((String) value);
                    break;
                case "status":
                    existingTask.setStatus((Boolean) value);
                    break;
                case "datefinished":
                    existingTask.setDatefinished((LocalDate) value);
                    break;
            }
        });
        return taskRepository.save(existingTask);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}

