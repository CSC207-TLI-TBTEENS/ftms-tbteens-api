package com.ftms.ftmsapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.ftms.ftmsapi.exception.ResourceNotFoundException;
import com.ftms.ftmsapi.model.Employee;
import com.ftms.ftmsapi.model.Job;
import com.ftms.ftmsapi.model.Task;
import com.ftms.ftmsapi.repository.JobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @PostMapping("/employees/jobs")
    public List<Employee> retrieveEmployeeFromJobs(@Valid @RequestBody Job job, List<Task> tasks) {
        ArrayList<Employee> employees = new ArrayList<>();
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

    // Delete a Job
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee (@PathVariable Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job", "id", id));

        jobRepository.delete(job);

        return ResponseEntity.ok().build();
    }
}
