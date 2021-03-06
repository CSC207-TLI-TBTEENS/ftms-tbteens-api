package com.ftms.ftmsapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.ftms.ftmsapi.model.*;
import com.ftms.ftmsapi.payload.ApiResponse;
import com.ftms.ftmsapi.payload.CreateJob;
import com.ftms.ftmsapi.payload.Time;
import com.ftms.ftmsapi.repository.*;

import com.ftms.ftmsapi.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    TaskRepository taskRepository;
    @Autowired
    TimesheetController timesheetController;
    @Autowired
    UserRepository<User> userRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmailService emailService;

    /**
     * Return a list containing all jobs.
     *
     * @return A list containing all jobs.
     */
    @GetMapping("/jobs")
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    /**
     * Saves the job job to the repository.
     *
     * @param createJob The job to be saved.
     * @return The job saved.
     */
    @PostMapping("/companies/{company_id}/jobs")
    public ResponseEntity createJob(@Valid @RequestBody CreateJob createJob,
                                    @PathVariable Long company_id) {
        try {
            Company company = companyRepository.getOne(company_id);
            Job createdJob = new Job(createJob.getJobTitle(), createJob.getDescription(),
                    createJob.getSiteName(), company);
            Job job = jobRepository.save(createdJob);
            return new ResponseEntity<Object>(job, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<Object>(new ApiResponse(false,
                    "Company not found!"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/jobs/{timesheet_id}/timesheet")
    public Job retrieveJobFromId(@PathVariable Long timesheet_id) {
        Timesheet timesheet = timesheetRepository.getOne(timesheet_id);
        return jobRepository.getOne(timesheet.getJobId());
    }


    /**
     * Delete the job with ID id, and then return its response entity.
     *
     * @param id The ID of the employee.
     * @return The response entity from the system.
     */
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<HttpStatus> deleteJob(@PathVariable Long id) {
        try {
            List<Timesheet> timesheets = timesheetRepository.findAll();
            for (Timesheet timesheet : timesheets){
                if (timesheet.getJobId().equals(id)){
                    timesheetRepository.delete(timesheet);
                }
            }
            Job job = jobRepository.getOne(id);
            jobRepository.delete(job);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/companies/{company_id}/jobs")
    public ResponseEntity getCompanyJobs(@PathVariable Long company_id) {
        try {
            Company company = companyRepository.getOne(company_id);
            List<Job> job = jobRepository.findByCompany(company);
            return new ResponseEntity<Object>(job, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<Object>(new ApiResponse(false,
                    "Company not found!"), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/jobs/{jobID}/assign/{employeeID}")
    public ResponseEntity assignJob(@PathVariable Long jobID, @PathVariable Long employeeID) {
        Timesheet timesheet = new Timesheet();
        List<Timesheet> timesheets = timesheetRepository.findAll();
        for (Timesheet storedTimesheet: timesheets){
            if (storedTimesheet.getEmployeeId().equals(employeeID) &&
                    storedTimesheet.getJobId().equals(jobID)){
                return new ResponseEntity<Object>(new ApiResponse(false,
                        "This job has already been assigned to this employee!")
                        , HttpStatus.BAD_REQUEST);
            }
        }

        if (jobID != null && employeeID != null) {
            // Finding employee and job based on ID.
            Employee employee = employeeRepository.getOne(employeeID);
            Job job = jobRepository.getOne(jobID);

            timesheet.setJob(job);
            timesheet.setEmployee(employee);
            timesheet.setApprovalStatus(0);
            timesheetRepository.save(timesheet);
        }

        try {
            Job job = jobRepository.getOne(jobID);
            Employee employee = employeeRepository.getOne(employeeID);
            String content = emailService.getJobAssignmentContent(employee.getFirstname(), job);
            emailService.sendEmail(employee.getFirstname(), employee.getEmail(),
                    content, "New Job Assignment");
            return new ResponseEntity<Object>(new ApiResponse(false,
                    "Job assinged to employee!") , HttpStatus.OK);
        } catch (EntityNotFoundException error) {
            error.printStackTrace();
            return new ResponseEntity<Object>(new ApiResponse(false,
                    "Employee/Job not found!")
                    , HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Return all the employees involved in the job with ID id in a list.
     *
     * @param id The ID of the job.
     * @return The list of employees from the job.
     */
    @GetMapping("/jobs/employees/{id}")
    public List<User> retrieveEmployeeFromJobs(@PathVariable Long id) {

        ArrayList<User> employees = new ArrayList<>();
        List<Timesheet> timesheetsJob = retrieveTimesheetsFromJob(id);

        Job storedjob = jobRepository.findById(id).orElse(null);

        if (storedjob == null) {
            System.out.println("Job not found!");
        } else {
            for (Timesheet timesheet : timesheetsJob) {
                User storedUser = userRepository.findById(timesheet.getEmployeeId()).orElse(null);
                if (storedUser != null)
                    employees.add(storedUser);
            }
        }
        return employees;
    }

    @GetMapping("/jobs/getfromemployee/{id}")
    public List<Job> retrieveJobsFromEmployee(@PathVariable Long id) {
        ArrayList<Job> jobs = new ArrayList<>();

        for (Timesheet timesheet : timesheetRepository.findAll()) {
            if (timesheet.getEmployeeId().equals(id)) {
                jobs.add(timesheet.getJob());
            }
        }
        return jobs;
    }

    /**
     * Return all the timesheets related to the job with ID job_id in a list.
     *
     * @param job_id The ID of the job we want to check.
     * @return A list containing all the timesheets related to the job.
     */
    @GetMapping("/timesheets/jobs")
    public List<Timesheet> retrieveTimesheetsFromJob(@Valid @RequestBody Long job_id) {
        ArrayList<Timesheet> timesheetsJob = new ArrayList<>();
        List<Timesheet> timesheets = timesheetRepository.findAll();

        Job storedjob = jobRepository.findById(job_id).orElse(null);

        if (storedjob == null) {
            System.out.println("Job not found!");
        } else {
            for (Timesheet timesheet : timesheets) {
                if (timesheet.getJobId().equals(job_id)){
                    timesheetsJob.add(timesheet);
                }
            }
        }
        return timesheetsJob;
    }

    // Delete jobs from an employee.
    @DeleteMapping("/jobs/{jobID}/remove/{employeeID}")
    public ResponseEntity<HttpStatus> deleteEmployeeFromJob(@PathVariable Long jobID, @PathVariable Long employeeID) {
        try {
            Employee employee = employeeRepository.getOne(employeeID);
            Job job = jobRepository.getOne(jobID);
            List<Timesheet> timesheets = timesheetRepository.findByEmployeeAndJob(employee, job); // Find timesheet by job and employee.
            for (Timesheet timesheet : timesheets) {
                    // Finding and removing all tasks associated with the timesheet.
                    List<Task> tasks = taskRepository.findByTimesheet(timesheet);
                    for (Task task : tasks) {
                        taskRepository.delete(task);
                    }
                    // Delete the timesheet.
                    timesheetRepository.delete(timesheet);
            }
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
