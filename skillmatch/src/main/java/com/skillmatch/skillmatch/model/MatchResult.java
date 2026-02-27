package com.skillmatch.skillmatch.model;

public class MatchResult {

    private Long candidateId;
    private String candidateName;
    private double score;
    private String explanation;

    public MatchResult(Long candidateId, String candidateName, double score, String explanation) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.score = score;
        this.explanation = explanation;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public double getScore() {
        return score;
    }

    public String getExplanation() {
        return explanation;
    }
}