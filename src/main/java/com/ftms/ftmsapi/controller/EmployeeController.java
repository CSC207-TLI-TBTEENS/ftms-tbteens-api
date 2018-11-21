package com.ftms.ftmsapi.controller;

import com.ftms.ftmsapi.model.Employee;
import com.ftms.ftmsapi.payload.UserSummary;
import com.ftms.ftmsapi.repository.EmployeeRepository;
import com.ftms.ftmsapi.security.CurrentUser;
import com.ftms.ftmsapi.security.EmployeePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser EmployeePrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(),
                currentUser.getUsername(), currentUser.getFirstname(), currentUser.getLastname());
        return userSummary;
    }

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
