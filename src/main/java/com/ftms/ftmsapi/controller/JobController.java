package com.ftms.ftmsapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.ftms.ftmsapi.exception.ResourceNotFoundException;
import com.ftms.ftmsapi.model.Employee;
import com.ftms.ftmsapi.model.Job;
import com.ftms.ftmsapi.model.Timesheet;
import com.ftms.ftmsapi.repository.JobRepository;
import com.ftms.ftmsapi.repository.TimesheetRepository;

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
    @Autowired
    TimesheetRepository timesheetRepository;
    @Autowired
    TimesheetController timesheetController;

    @GetMapping("/employees/jobs")
    public List<Employee> retrieveEmployeeFromJobs(@Valid @RequestBody Job job) {
        ArrayList<Employee> employees = new ArrayList<>();
        List<Timesheet> timesheetsJob = retrieveTimesheetsFromJob(job);
        if (!jobRepository.findAll().contains(job)) {
            System.out.println("Job not found!");
        }
        else {
            for (Timesheet timesheet : timesheetsJob) {
                employees.add(timesheet.getEmployee());
            }
        }
        return employees;
    }

    @GetMapping("/timesheets/jobs")
    public List<Timesheet> retrieveTimesheetsFromJob(@Valid @RequestBody Job job) {
        ArrayList<Timesheet> timesheetsJob = new ArrayList<>();
        List<Timesheet> timesheets = timesheetRepository.findAll();
        if (!jobRepository.findAll().contains(job)) {
            System.out.println("Job not found!");
        }
        else {
            for (Timesheet timesheet : timesheets) {
                if (timesheet.getJob().getId() == job.getId()){
                    timesheetsJob.add(timesheet);
                }
                
            }
        }
        return timesheetsJob;
    }

    // Create a new company
    @PostMapping("/jobs")
    public Job createJob(@Valid @RequestBody Job job) {
        return jobRepository.save(job);
    }

    @PostMapping("/jobsassign")
    void assignJob(@Valid @RequestBody Job job, Employee employee) {
        if (!jobRepository.findAll().contains(job)) {
            System.out.println("Job not found!");
        }
        else {
            Timesheet timesheet = new Timesheet();
            timesheet.setJob(job);
            timesheet.setEmployee(employee);
            timesheet.setApprovalStatus("Not reviewed");
            timesheetController.createTimesheet(timesheet);
        }
        
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
