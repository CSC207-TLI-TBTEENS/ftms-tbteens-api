package com.ftms.ftmsapi.controller;

import com.ftms.ftmsapi.model.User;
import com.ftms.ftmsapi.payload.UserSummary;
import com.ftms.ftmsapi.repository.UserRepository;
import com.ftms.ftmsapi.security.CurrentUser;
import com.ftms.ftmsapi.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import com.ftms.ftmsapi.model.Job;
import com.ftms.ftmsapi.model.Task;

import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/employees")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/me")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(),
                currentUser.getUsername(), currentUser.getFirstname(),
                currentUser.getLastname(), currentUser.getRole());
        return userSummary;
    }

    // Get all employees
    @GetMapping("")
    public List<User> getAllEmployees() {
        return userRepository.findAll();
    }

    // Create a new employee
    @PostMapping("")
    public User createEmployee(@Valid @RequestBody User employee) {
        return userRepository.save(employee);
    }

    // Delete an employee
    @DeleteMapping("/{id}")
    public List<String> deleteEmployee (@PathVariable Long id) {
        ArrayList<String> success =  new ArrayList<>();
        ArrayList<String> failure =  new ArrayList<>();
        success.add("202 ACCEPTED");
        success.add("400 BAD REQUEST");

        try {
            User employee = userRepository.getOne(id);
            userRepository.delete(employee);
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
            User findEmployee = userRepository.getOne(id);

            findEmployee.setFirstname(firstName);
            findEmployee.setLastname(lastName);
            findEmployee.setNumber(phone);
            userRepository.save(findEmployee);
            return value;
//            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (EntityNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return value;
        }
    }

    @PostMapping("/employees/jobs")
    public List<Job> retrieveJobsFromEmployee(@Valid @RequestBody User user, List<Task> tasks) {
        ArrayList<Job> jobs = new ArrayList<>();
        if (!userRepository.findAll().contains(user)) {
            System.out.println("User not found!");
        }
        else {
            for (Task task : tasks) {
                if (task.getEmployee().getId().equals(user.getId())) {
                    jobs.add(task.getJob());
                }
            }
        }
        return jobs;
    }
}
