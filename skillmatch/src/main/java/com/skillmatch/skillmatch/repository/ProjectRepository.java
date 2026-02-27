package com.skillmatch.skillmatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillmatch.skillmatch.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	List<Project> findByCompanyId(Long companyId);
}