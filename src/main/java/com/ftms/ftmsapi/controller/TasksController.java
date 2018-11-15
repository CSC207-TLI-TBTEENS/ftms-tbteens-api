package com.ftms.ftmsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TasksController {
    @Autowired
    TaskRespository taskRespository;

    // Get all tasks
    @GetMapping("/tasks")
    public List<Task> getAllTasks() { return taskRespository.findAll(); }

    // Create a new task
    @PostMapping("/tasks")
    public Task createTask(@Valid @RequestBody Task task) { return taskRepository.save(task); }


}
