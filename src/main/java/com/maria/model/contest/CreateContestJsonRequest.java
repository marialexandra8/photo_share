package com.maria.model.contest;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.maria.serializer.FullDateDeserializer;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created on 8/20/2017.
 */
public class CreateContestJsonRequest {
    private String name;
    private String description;
    private LocalDate deadline;

    public String getName() {
        return name;
    }

    public CreateContestJsonRequest setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CreateContestJsonRequest setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    @JsonDeserialize(using = FullDateDeserializer.class)
    public CreateContestJsonRequest setDeadline(LocalDate deadline) {
        this.deadline = deadline;
        return this;
    }
}
