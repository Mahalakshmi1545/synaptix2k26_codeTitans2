package com.skillmatch.skillmatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.skillmatch.skillmatch.model.Candidate;
import com.skillmatch.skillmatch.repository.CandidateRepository;

@RestController
@RequestMapping("/candidates")

public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    // Add Candidate
    @PostMapping("/add")
    public Candidate addCandidate(@RequestBody Candidate candidate) {
        return candidateRepository.save(candidate);
    }

    // Get All Candidates
    @GetMapping("/all")
    public List<Candidate> getAllCandidates() {
        return candidateRepository.findAll();
    }
    @PostMapping("/register")
    public String register(@RequestBody Candidate candidate) {

        if (candidateRepository.findByEmail(candidate.getEmail()) != null) {
            return "Email already exists!";
        }

        candidateRepository.save(candidate);
        return "Registration successful!";
    }

    @PostMapping("/login")
    public String login(@RequestBody Candidate loginData) {

        Candidate existing = candidateRepository.findByEmail(loginData.getEmail());

        if (existing == null) {
            return "User not found!";
        }

        if (!existing.getPassword().equals(loginData.getPassword())) {
            return "Invalid password!";
        }

        return "Login successful!";
    }
    @GetMapping("/details/{id}")
    public Candidate getCandidateDetails(@PathVariable Long id) {
        return candidateRepository.findById(id).orElse(null);
    }
    @GetMapping("/getByEmail/{email}")
    public Candidate getByEmail(@PathVariable String email) {
        return candidateRepository.findByEmail(email);
    }
    @PutMapping("/update/{id}")
    public String updateCandidate(@PathVariable Long id, @RequestBody Candidate updatedCandidate) {

        Candidate existing = candidateRepository.findById(id).orElse(null);

        if (existing == null) {
            return "Candidate not found!";
        }

        // Email should not change to another existing email
        if (!existing.getEmail().equals(updatedCandidate.getEmail())) {
            if (candidateRepository.findByEmail(updatedCandidate.getEmail()) != null) {
                return "Email already exists!";
            }
        }

        existing.setName(updatedCandidate.getName());
        existing.setEmail(updatedCandidate.getEmail());
        existing.setPhone(updatedCandidate.getPhone());
        existing.setLocation(updatedCandidate.getLocation());
        existing.setSkills(updatedCandidate.getSkills());
        existing.setExperience(updatedCandidate.getExperience());

        candidateRepository.save(existing);

        return "Profile updated successfully!";
    }
}