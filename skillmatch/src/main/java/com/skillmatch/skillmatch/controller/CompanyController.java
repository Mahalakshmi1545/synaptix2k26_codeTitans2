package com.skillmatch.skillmatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.skillmatch.skillmatch.model.Company;
import com.skillmatch.skillmatch.repository.CompanyRepository;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping("/register")
    public String register(@RequestBody Company company) {

        if (companyRepository.findByEmail(company.getEmail()) != null) {
            return "Email already exists!";
        }

        companyRepository.save(company);
        return "Registration successful!";
    }

    @PostMapping("/login")
    public String login(@RequestBody Company loginData) {

        Company existing = companyRepository.findByEmail(loginData.getEmail());

        if (existing == null) {
            return "Company not found!";
        }

        if (!existing.getPassword().equals(loginData.getPassword())) {
            return "Invalid password!";
        }

        return "Login successful!";
    }
}