package com.skillmatch.skillmatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillmatch.skillmatch.model.Selection;
import java.util.List;

public interface SelectionRepository extends JpaRepository<Selection, Long> {

    List<Selection> findByCandidateId(Long candidateId);
    boolean existsByCandidateIdAndProjectId(Long candidateId, Long projectId);
}