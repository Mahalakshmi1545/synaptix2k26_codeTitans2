package com.skillmatch.skillmatch.model;

import jakarta.persistence.*;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String requiredSkills;
    private int minExperience;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Project() {}

    public Project(Long id, String title, String requiredSkills, int minExperience) {
        this.id = id;
        this.title = title;
        this.requiredSkills = requiredSkills;
        this.minExperience = minExperience;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public int getMinExperience() {
        return minExperience;
    }

    public void setMinExperience(int minExperience) {
        this.minExperience = minExperience;
    }
}