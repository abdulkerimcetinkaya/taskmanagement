package com.example.service.Impl;

import com.example.core.exception.TaskNotFoundException;
import com.example.model.Task;
import com.example.repository.TaskRepository;
import com.example.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;


    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        logger.debug("Retrieving all tasks from database");
        return taskRepository.findAll();
    }

    @Override
    public Optional<Task> getTaskById(Long id) {
        logger.debug("Retrieving task with id: {}", id);
        return taskRepository.findById(id);
    }

    @Override
    public Task createTask(Task task) {
        logger.debug("Saving new task to database");
        Task savedTask = taskRepository.save(task);
        logger.debug("Task saved with id: {}", savedTask.getId());
        return savedTask;
    }

    @Override
    public Task updateTask(Long id, Task taskDetails) {
        logger.debug("Updating task with id: {}", id);
        return taskRepository.findById(id).map(task -> {
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setStatus(taskDetails.getStatus());
            Task updatedTask = taskRepository.save(task);
            logger.debug("Task updated with id: {}", updatedTask.getId());
            return updatedTask;
        }).orElseThrow(() -> {
            logger.error("Task not found with id: {}", id);
            return new TaskNotFoundException(id);
        });
    }

    @Override
    public void deleteTask(Long id) {
        logger.debug("Deleting task with id: {}", id);
        taskRepository.deleteById(id);
        logger.debug("Task deleted with id: {}", id);
    }
}
