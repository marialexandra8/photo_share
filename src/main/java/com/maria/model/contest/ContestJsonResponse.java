package com.maria.model.contest;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.maria.serializer.FullDateSerializer;

import java.time.LocalDate;

/**
 * Created on 8/20/2017.
 */
public class ContestJsonResponse {
    private Integer id;
    private String name;
    private String description;
    private String logoPath;
    private LocalDate deadline;

    public ContestJsonResponse(Contest contest) {
        this
                .setId(contest.getId())
                .setDeadline(contest.getDeadline())
                .setLogoPath(contest.getLogoPath())
                .setDescription(contest.getDescription())
                .setName(contest.getName());
    }

    public ContestJsonResponse() {
    }

    public String getLogoPath() {
        return logoPath;
    }

    public ContestJsonResponse setLogoPath(String logoPath) {
        this.logoPath = logoPath;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public ContestJsonResponse setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ContestJsonResponse setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ContestJsonResponse setDescription(String description) {
        this.description = description;
        return this;
    }

    @JsonSerialize(using = FullDateSerializer.class)
    public LocalDate getDeadline() {
        return deadline;
    }

    public ContestJsonResponse setDeadline(LocalDate deadline) {
        this.deadline = deadline;
        return this;
    }
}
