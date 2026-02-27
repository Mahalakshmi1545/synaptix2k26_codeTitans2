package com.skillmatch.skillmatch.controller;
import com.skillmatch.skillmatch.model.Candidate;
import com.skillmatch.skillmatch.model.MatchResult;
import com.skillmatch.skillmatch.model.Project;
import com.skillmatch.skillmatch.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*; 
import java.util.*;
import java.util.stream.Collectors; 
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/match")
public class MatchingController {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping("/{projectId}")
    public List<MatchResult> matchCandidates(@PathVariable Long projectId) {

        Project project = projectRepository.findById(projectId).orElse(null);
        if (project == null) {
            return Collections.emptyList();
        }

        List<Candidate> candidates = candidateRepository.findAll()
                .stream()
                .filter(c -> c.getSkills() != null)
                .collect(Collectors.toList());

        List<MatchResult> results = new ArrayList<>();

        List<String> requiredSkills = Arrays.stream(project.getRequiredSkills().split(","))
                .map(skill -> skill.trim().toLowerCase())
                .collect(Collectors.toList());

        for (Candidate candidate : candidates) {

            List<String> candidateSkills = Arrays.stream(candidate.getSkills().split(","))
                    .map(skill -> skill.trim().toLowerCase())
                    .collect(Collectors.toList());

            long matchedSkills = requiredSkills.stream()
                    .filter(candidateSkills::contains)
                    .count();

            double skillScore = ((double) matchedSkills / requiredSkills.size()) * 70;

            double experienceBonus = 0;
            if (candidate.getExperience() >= project.getMinExperience()) {
                experienceBonus = 30;
            }

            double finalScore = skillScore + experienceBonus;
            if (finalScore == 0) {
                continue; // skip zero score candidates
            }
            String explanation = "Matched " + matchedSkills + " out of "
                    + requiredSkills.size() + " required skills. "
                    + "Experience bonus: " + experienceBonus;

            results.add(new MatchResult(
                    candidate.getId(),
                    candidate.getName(),
                    finalScore,
                    explanation
            ));
        }

        results.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));

        return results;
    }
}