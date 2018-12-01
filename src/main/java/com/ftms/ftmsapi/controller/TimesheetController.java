package com.ftms.ftmsapi.controller;

import javax.validation.Valid;

import com.ftms.ftmsapi.model.Timesheet;
import com.ftms.ftmsapi.repository.TimesheetRepository;
import com.ftms.ftmsapi.model.Job;
import com.ftms.ftmsapi.repository.JobRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TimesheetController {

    @Autowired
    TimesheetRepository timesheetRepository;

    @Autowired
    JobRepository jobRepository;

    /**
     * Saving the Timesheet timesheet to the database.
     *
     * @param timesheet The timesheet we wants to save to the database.
     * @return The timesheet saved.
     */
    @PostMapping("/save")
    public Timesheet createTimesheet(@Valid @RequestBody Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }

    /**
     * Get all the timesheets in a list.
     *
     * @return List of all timesheets in the repository.
     */
    @PostMapping("/get")
    public List<Timesheet> getTimesheet(){
        return timesheetRepository.findAll();
    }

    /**
     * Get all of jobs in the timesheet with id timesheet_id in a list.
     *
     * @param timesheet_id The timesheet ID we want to check the timesheet.
     * @return The list of jobs in the timesheet.
     */
    @RequestMapping("/get/job_by_timesheet_id")
    public List<Job> getJobsByTimesheetId(@Valid @RequestBody Long timesheet_id){
        return jobRepository.findJobsFromTimesheetId(timesheet_id);
    }

    //Get timesheet from job and employee id

    /**
     * Get all of the timesheets
     *
     * @param employee_id
     * @param job_id
     * @return
     */
    @RequestMapping("/get/timesheet_by_employee_and_job_id")
    public List<Timesheet> getTimesheetByEmployeeAndJobId(@Valid @RequestBody Long employee_id, Long job_id){
        return timesheetRepository.findTimesheetFromEmployeeIdAndJobId(employee_id, job_id);}

    // Approve the timesheet.
    @PostMapping("/approve")
    public void approve(Long timesheetId) {
        Timesheet ts = timesheetRepository.getOne(timesheetId);
        ts.setApprovalStatus("Approved");
        timesheetRepository.save(ts);
    }

    // Reject the timesheet.
    @PostMapping("/reject")
    public void reject(Long timesheetId) {
        Timesheet ts = timesheetRepository.getOne(timesheetId);
        ts.setApprovalStatus("Rejected");
        timesheetRepository.save(ts);
    }

}