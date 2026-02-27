package com.skillmatch.skillmatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillmatch.skillmatch.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByEmail(String email);
}