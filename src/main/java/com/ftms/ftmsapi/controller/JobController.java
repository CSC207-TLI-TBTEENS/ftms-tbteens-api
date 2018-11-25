package com.ftms.ftmsapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.ftms.ftmsapi.exception.ResourceNotFoundException;
import com.ftms.ftmsapi.model.Job;
import com.ftms.ftmsapi.model.Task;
import com.ftms.ftmsapi.model.User;
import com.ftms.ftmsapi.repository.JobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JobController {
    @Autowired
    JobRepository jobRepository;

    @GetMapping("/employees/jobs")
    public List<User> retrieveEmployeeFromJobs(@Valid @RequestBody Job job, List<Task> tasks) {
        ArrayList<User> employees = new ArrayList<>();
        if (!jobRepository.findAll().contains(job)) {
            System.out.println("Job not found!");
        }
        else {
            for (Task task : tasks) {
                employees.add(task.getEmployee());
            }
        }
        return employees;
    }

    @GetMapping("/tasks/jobs")
    public List<Task> retrieveTasksFromJobs(@Valid @RequestBody Job job, List<Task> tasks) {
        ArrayList<Task> jobtasks = new ArrayList<>();
        if (!jobRepository.findAll().contains(job)) {
            System.out.println("Job not found!");
        }
        else {
            for (Task task : tasks) {
                if (task.getJob().getId() == job.getId()){
                    jobtasks.add(task);
                }
                
            }
        }
        return jobtasks;
    }

    // Create a new company
    @PostMapping("/jobs")
    public Job createJob(@Valid @RequestBody Job job) {
        System.out.println("HEY");
        return jobRepository.save(job);
    }

    @PostMapping("/jobsassign")
    public List<Task> assignJob(@Valid @RequestBody Job job, List<Task> tasks) {
        ArrayList<Task> jobtasks = new ArrayList<>();
        if (!jobRepository.findAll().contains(job)) {
            System.out.println("Job not found!");
        }
        else {
            for (Task task : tasks) {
                if (task.getJob().getId().equals(job.getId())) {
                    jobtasks.add(task);
                }
                
            }
        }
        return jobtasks;
    }

     // Get all Jobs
     @GetMapping("/jobs")
     public List<Job> getAllJobs() {
         return jobRepository.findAll();
     }

    // Delete a Job
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJob (@PathVariable Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job", "id", id));

        jobRepository.delete(job);

        return ResponseEntity.ok().build();
    }
}
