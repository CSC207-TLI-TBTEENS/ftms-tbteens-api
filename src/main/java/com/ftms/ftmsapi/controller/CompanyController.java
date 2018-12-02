package com.ftms.ftmsapi.controller;

import com.ftms.ftmsapi.model.ClientUser;
import com.ftms.ftmsapi.model.Company;
import com.ftms.ftmsapi.repository.CompanyRepository;
import com.ftms.ftmsapi.services.EmailService;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/api/companies")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    private EmailService emailService;

    Hashids hashids = new Hashids("FTMS", 10);

    // Get all companies
    @GetMapping("")
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // Create a new company
    @PostMapping("")
    public Company createCompany(@Valid @RequestBody Company company) {
        // Hashing ClientUser id
        Company createdCompany = companyRepository.save(company);
        String id = hashids.encode(createdCompany.getId());
        String message = "Welcome " + company.getName()  +
                "\n We’re excited you chose Norweld. Please follow this link to set " +
                "up your company account: " +
                "http://localhost:3000/companysignup/" + id +
                "\n If you encounter any problems, please contact admin." +
                "\n\n - Nor-Weld";
        emailService.prepareAndSend(createdCompany.getEmail(),
                "Welcome Email", message);
        return createdCompany;
    }

    // Delete a company
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee (@PathVariable Long id) {
        try {
            Company company = companyRepository.getOne(id);
            companyRepository.delete(company);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editCompany (@PathVariable Long id, @RequestParam String name,
                                               @RequestParam String logo, @RequestParam String email,
                                               @RequestParam String phone) {
        ArrayList<String> value = new ArrayList<>();
        try {
            Company findCompany = companyRepository.getOne(id);
            findCompany.setName(name);
            findCompany.setLogo(logo);
            findCompany.setEmail(email);
            findCompany.setNumber(phone);
            System.out.println(findCompany.getName());
            companyRepository.save(findCompany);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
