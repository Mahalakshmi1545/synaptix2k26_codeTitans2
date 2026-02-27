package com.skillmatch.skillmatch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.skillmatch.skillmatch.model.Company;
import com.skillmatch.skillmatch.model.Project;
import com.skillmatch.skillmatch.model.Selection;
import com.skillmatch.skillmatch.repository.CompanyRepository;
import com.skillmatch.skillmatch.repository.ProjectRepository;
import com.skillmatch.skillmatch.repository.SelectionRepository;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private SelectionRepository SelectionRepository;

    @PostMapping("/add/{companyEmail}")
    public Project addProject(@PathVariable String companyEmail,
                              @RequestBody Project project) {

        Company company = companyRepository.findByEmail(companyEmail);

        project.setCompany(company);

        return projectRepository.save(project);
    }
    @PutMapping("/update/{projectId}")
    public String updateProject(@PathVariable Long projectId,
                                @RequestBody Project updatedProject) {

        Project existing = projectRepository.findById(projectId).orElse(null);

        if (existing == null) {
            return "Project not found!";
        }

        existing.setTitle(updatedProject.getTitle());
        existing.setRequiredSkills(updatedProject.getRequiredSkills());
        existing.setMinExperience(updatedProject.getMinExperience());

        projectRepository.save(existing);

        return "Project updated successfully!";
    }
    @Autowired
    private SelectionRepository selectionRepository;

    @PostMapping("/select")
    public String selectCandidate(@RequestBody Selection selection) {

        Long candidateId = selection.getCandidateId();
        Long projectId = selection.getProjectId();

        boolean exists = selectionRepository
                .existsByCandidateIdAndProjectId(candidateId, projectId);

        if (exists) {
            return "Already Selected";
        }

        selection.setMessage(
                "You have been selected by " + selection.getCompanyEmail()
        );

        selectionRepository.save(selection);

        return "Candidate Selected Successfully";
    }    @GetMapping("/notifications/{candidateId}")
    public List<Selection> getNotifications(@PathVariable Long candidateId) {
        return selectionRepository.findByCandidateId(candidateId);
    }
    @GetMapping("/company/{email}")
    public List<Project> getCompanyProjects(@PathVariable String email) {

        Company company = companyRepository.findByEmail(email);

        return projectRepository.findByCompanyId(company.getId());
    }
    @DeleteMapping("/delete/{projectId}")
    public String deleteProject(@PathVariable Long projectId) {

        Project project = projectRepository.findById(projectId).orElse(null);

        if (project == null) {
            return "Project not found!";
        }

        projectRepository.delete(project);

        return "Project deleted successfully!";
    }

    // Add Project
    @PostMapping("/add")
    public Project addProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    // Get All Projects
    @GetMapping("/all")
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}