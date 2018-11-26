package com.ftms.ftmsapi.controller;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.ftms.ftmsapi.exception.ResourceNotFoundException;
import com.ftms.ftmsapi.model.Company;
import com.ftms.ftmsapi.model.Job;
import com.ftms.ftmsapi.model.Selection;
import com.ftms.ftmsapi.model.Timesheet;
import com.ftms.ftmsapi.model.User;
import com.ftms.ftmsapi.repository.JobRepository;
import com.ftms.ftmsapi.repository.TimesheetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
 
    private Job curJob;
    private User curEmp;

    @PutMapping("/jobset")
    void settingJob(@RequestBody String selection) throws JsonParseException, JsonMappingException, IOException {
        curJob = new ObjectMapper().readValue(selection, Job.class);
        
    }
    @PutMapping("/empset")
    void settingEmp(@RequestBody String selection) throws JsonParseException, JsonMappingException, IOException {
        curEmp = new ObjectMapper().readValue(selection, User.class);
    }
    @GetMapping("/employees/jobs")
    public List<User> retrieveEmployeeFromJobs(@Valid @RequestBody Job job) {
        ArrayList<User> employees = new ArrayList<>();
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
                if (timesheet.getJobID() == job.getId()){
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

    @PutMapping("/jobsassign")
    void assignJob(@Valid @RequestBody Selection selection) {
        System.out.println(selection.getEmployee().getFirstname());
        System.out.println(selection.getJob().getDescription());
        Timesheet timesheet = new Timesheet();
        timesheet.setJobID(selection.getJob().getId());
        timesheet.setEmployeeID(selection.getEmployee().getId());
        timesheet.setApprovalStatus("Not reviewed");
        timesheetController.createTimesheet(timesheet);
        
        System.out.println(timesheet);
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
