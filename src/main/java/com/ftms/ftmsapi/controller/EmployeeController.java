package com.ftms.ftmsapi.controller;

import com.ftms.ftmsapi.model.Employee;
import com.ftms.ftmsapi.payload.UserSummary;
import com.ftms.ftmsapi.repository.EmployeeRepository;
import com.ftms.ftmsapi.security.CurrentUser;
import com.ftms.ftmsapi.security.EmployeePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import com.ftms.ftmsapi.model.Job;
import com.ftms.ftmsapi.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser EmployeePrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(),
                currentUser.getUsername(), currentUser.getFirstname(), currentUser.getLastname());
        return userSummary;
    }

    // Get all employees
    @GetMapping("")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Create a new employee
    @PostMapping("")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // Delete an employee
    @DeleteMapping("/{id}")
    public List<String> deleteEmployee (@PathVariable Long id) {
        ArrayList<String> success =  new ArrayList<>();
        ArrayList<String> failure =  new ArrayList<>();
        success.add("202 ACCEPTED");
        success.add("400 BAD REQUEST");

        try {
            Employee employee = employeeRepository.getOne(id);
            employeeRepository.delete(employee);
            return success;
        } catch (EntityNotFoundException e) {
            return failure;
        }
    }

    // Edit an employee's details
    @PutMapping("/{id}")
    public List<String> editEmployee (@PathVariable Long id, @RequestParam String firstName,
                                      @RequestParam String lastName, @RequestParam String email,
                                      @RequestParam String phone) {
        ArrayList<String> value = new ArrayList<>();
        value.add("Hello!");
        try {
            Employee findEmployee = employeeRepository.getOne(id);

            findEmployee.setFirstname(firstName);
            findEmployee.setLastname(lastName);
            findEmployee.setEmail(email);
            findEmployee.setNumber(phone);
            employeeRepository.save(findEmployee);
            return value;
//            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (EntityNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return value;
        }
    }

    @PostMapping("/employees/jobs")
    public List<Job> retrieveJobsFromEmployee(@Valid @RequestBody Employee employee, List<Task> tasks) {
        ArrayList<Job> jobs = new ArrayList<>();
        if (!employeeRepository.findAll().contains(employee)) {
            System.out.println("Employee not found!");
        }
        else {
            for (Task task : tasks) {
                if (task.getEmployee().getId().equals(employee.getId())) {
                    jobs.add(task.getJob());
                }
            }
        }
        return jobs;
    }
}
