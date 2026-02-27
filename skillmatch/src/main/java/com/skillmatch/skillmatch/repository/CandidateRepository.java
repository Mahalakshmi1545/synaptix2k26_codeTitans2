package com.skillmatch.skillmatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillmatch.skillmatch.model.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    Candidate findByEmail(String email);

}