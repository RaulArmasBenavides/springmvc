package com.maestria.springmvc.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.maestria.springmvc.controller.exception.ResourceNotFoundException;
import com.maestria.springmvc.model.Task;
import com.maestria.springmvc.repository.TaskRepository;
import com.maestria.springmvc.service.TaskService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task update(Long id, Task task) {
        Task existingTask = findById(id);
        existingTask.setTitle(task.getTitle());
        existingTask.setBody(task.getBody());
        existingTask.setStatus(task.getStatus());
        existingTask.setDatefinished(task.getDatefinished());

        return taskRepository.save(existingTask);
    }

    @Override
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
                default:
                    // opcional: ignorar campos desconocidos
                    break;
            }
        });

        return taskRepository.save(existingTask);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}