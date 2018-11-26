package com.ftms.ftmsapi.controller;

import javax.validation.Valid;

import com.ftms.ftmsapi.model.Timesheet;
import com.ftms.ftmsapi.repository.TimesheetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TimesheetController {

    @Autowired
    TimesheetRepository timesheetRepository;

    // Create a new employee
    @PostMapping("")
    public Timesheet createTimesheet(@Valid @RequestBody Timesheet timesheet) {
        System.out.println("jnadkfbgfbgsdn");
        return timesheetRepository.save(timesheet);
    }
}