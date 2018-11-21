package com.ftms.ftmsapi.controller;

import com.ftms.ftmsapi.exception.ResourceNotFoundException;
import com.ftms.ftmsapi.model.Company;
import com.ftms.ftmsapi.payload.ApiResponse;
import com.ftms.ftmsapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;

    // Get all companies
    @GetMapping("/companies")
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // Create a new company
    @PostMapping("/companies")
    public Company createCompany(@Valid @RequestBody Company company) {
        return companyRepository.save(company);
    }

    // Delete a company
    @DeleteMapping("/companies/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee (@PathVariable Long id) {
        try {
            Company company = companyRepository.getOne(id);
            companyRepository.delete(company);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/companies/{id}")
    public List<String> editCompany (@PathVariable Long id, @RequestParam String name,
                                     @RequestParam String logo, @RequestParam String email,
                                     @RequestParam String phone) {
        ArrayList<String> value = new ArrayList<>();
        value.add("Hello!");
        try {
            Company findCompany = companyRepository.getOne(id);
            findCompany.setName(name);
            findCompany.setLogo(logo);
            findCompany.setEmail(email);
            findCompany.setNumber(phone);
            System.out.println(findCompany.getName());
            companyRepository.save(findCompany);
            return value;
//            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (EntityNotFoundException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            return value;
        }
    }
}
