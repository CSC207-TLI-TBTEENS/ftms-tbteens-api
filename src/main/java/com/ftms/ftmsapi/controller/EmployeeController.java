package com.ftms.ftmsapi.controller;

import com.ftms.ftmsapi.model.Employee;
import com.ftms.ftmsapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    // Get all employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Create a new employee
    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }
}
