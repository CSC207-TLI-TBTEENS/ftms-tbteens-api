package com.ftms.ftmsapi.controller;

import com.ftms.ftmsapi.model.Timesheet;
import com.ftms.ftmsapi.repository.TimesheetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TimesheetController {
    @Autowired
    TimesheetRepository timesheetRepository;

    // Get all timesheets
    @GetMapping("/timesheets")
    public List<Timesheet> getAllTimesheets() {
        return timesheetRepository.findAll();
    }

    // Create a new timesheet (temp)
    @PostMapping("/timesheets")
    public Timesheet createTimesheets(@Valid @RequestBody Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }
    
}
