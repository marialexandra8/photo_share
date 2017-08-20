package com.maria.model.contest;

import java.time.LocalDate;

/**
 * Created on 8/20/2017.
 */
public class CreateContestRequest {
    private String name;
    private String description;
    private LocalDate deadline;

    public String getName() {
        return name;
    }

    public CreateContestRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateContestRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public CreateContestRequest setDeadline(LocalDate deadline) {
        this.deadline = deadline;
        return this;
    }
}
