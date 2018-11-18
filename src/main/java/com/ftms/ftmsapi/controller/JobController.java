package com.ftms.ftmsapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ftms.ftmsapi.model.Job;
import com.ftms.ftmsapi.repository.JobRepository;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JobController {
    @Autowired
    JobRepository jobRepository;

    // Get all tasks
    @GetMapping("/jobs")
    public List<Job> getAllTasks() { return jobRepository.findAll(); }

    // Create a new task
    @PostMapping("/jobs")
    public Job createJob(@Valid @RequestBody Job job) { return jobRepository.save(job); }
}
