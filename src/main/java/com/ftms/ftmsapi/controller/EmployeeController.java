package com.ftms.ftmsapi.controller;

import com.ftms.ftmsapi.model.Employee;
import com.ftms.ftmsapi.model.Job;
import com.ftms.ftmsapi.model.Task;
import com.ftms.ftmsapi.repository.EmployeeRepository;
import com.ftms.ftmsapi.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import javax.validation.Valid;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

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
    public ResponseEntity<?> deleteEmployee (@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        employeeRepository.delete(employee);

        return ResponseEntity.ok().build();
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
