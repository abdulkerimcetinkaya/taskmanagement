package com.example.controller;

import com.example.core.exception.TaskNotFoundException;
import com.example.dto.TaskDTO;
import com.example.mapper.TaskMapper;
import com.example.model.Task;
import com.example.service.TaskService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        logger.info("Fetching all tasks");
        List<Task> tasks = taskService.getAllTasks();
        List<TaskDTO> taskDTOs = tasks.stream()
                .map(taskMapper::toDTO)
                .collect(Collectors.toList());
        logger.info("Successfully fetched {} tasks", taskDTOs.size());
        return ResponseEntity.ok(taskDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        logger.info("Fetching task with id: {}", id);
        Task task = taskService.getTaskById(id)
                .orElseThrow(() -> {
                    logger.error("Task not found with id: {}", id);
                    return new TaskNotFoundException(id);
                });
        TaskDTO taskDTO = taskMapper.toDTO(task);
        logger.info("Successfully fetched task with id: {}", id);
        return ResponseEntity.ok(taskDTO);
    }


    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO taskDTO) {
        logger.info("Creating new task with title: {}", taskDTO.getTitle());
        Task task = taskMapper.toEntity(taskDTO);
        Task createdTask = taskService.createTask(task);
        TaskDTO createdTaskDTO = taskMapper.toDTO(createdTask);
        logger.info("Successfully created task with id: {}", createdTaskDTO.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTaskDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @Valid @RequestBody TaskDTO taskDTO) {
        logger.info("Updating task with id: {}", id);
        Task task = taskService.getTaskById(id)
                .orElseThrow(() -> {
                    logger.error("Task not found with id: {}", id);
                    return new TaskNotFoundException(id);
                });
        taskMapper.updateEntityFromDTO(taskDTO, task);
        Task updatedTask = taskService.updateTask(id, task);
        TaskDTO updatedTaskDTO = taskMapper.toDTO(updatedTask);
        logger.info("Successfully updated task with id: {}", id);
        return ResponseEntity.ok(updatedTaskDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        logger.info("Deleting task with id: {}", id);
        if (!taskService.getTaskById(id).isPresent()) {
            logger.error("Task not found with id: {}", id);
            throw new TaskNotFoundException(id);
        }
        taskService.deleteTask(id);
        logger.info("Successfully deleted task with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}
