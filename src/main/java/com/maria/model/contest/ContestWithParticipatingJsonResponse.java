package com.maria.model.contest;

import java.time.LocalDate;

/**
 * Created on 9/1/2017.
 */
public class ContestWithParticipatingJsonResponse {
    private Integer id;
    private String name;
    private String description;
    private String logoPath;
    private LocalDate deadline;
    private Boolean userIsParticipating;

    public ContestWithParticipatingJsonResponse(Contest contest) {
        this
                .setDeadline(contest.getDeadline())
                .setDescription(contest.getDescription())
                .setId(contest.getId())
                .setLogoPath(contest.getLogoPath())
                .setName(contest.getName());
    }

    public Integer getId() {
        return id;
    }

    public ContestWithParticipatingJsonResponse setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ContestWithParticipatingJsonResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ContestWithParticipatingJsonResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public ContestWithParticipatingJsonResponse setLogoPath(String logoPath) {
        this.logoPath = logoPath;
        return this;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public ContestWithParticipatingJsonResponse setDeadline(LocalDate deadline) {
        this.deadline = deadline;
        return this;
    }

    public Boolean getUserIsParticipating() {
        return userIsParticipating;
    }

    public ContestWithParticipatingJsonResponse setUserIsParticipating(Boolean userIsParticipating) {
        this.userIsParticipating = userIsParticipating;
        return this;
    }
}
