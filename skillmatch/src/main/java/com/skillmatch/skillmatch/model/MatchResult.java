package com.skillmatch.skillmatch.model;

public class MatchResult {

    private Long candidateId;
    private String candidateName;
    private double score;
    private String explanation;
    private boolean selected;

    public MatchResult(Long candidateId,
                       String candidateName,
                       double score,
                       String explanation,
                       boolean selected) {

        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.score = score;
        this.explanation = explanation;
        this.selected = selected;
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

    public boolean isSelected() {
        return selected;
    }
}