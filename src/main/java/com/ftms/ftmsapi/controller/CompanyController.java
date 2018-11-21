package com.ftms.ftmsapi.controller;

import com.ftms.ftmsapi.exception.ResourceNotFoundException;
import com.ftms.ftmsapi.model.Company;
import com.ftms.ftmsapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<?> deleteEmployee (@PathVariable Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));

        companyRepository.delete(company);

        return ResponseEntity.ok().build();
    }
}
