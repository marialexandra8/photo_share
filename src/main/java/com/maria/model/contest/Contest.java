package com.maria.model.contest;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created on 8/20/2017.
 */
public class Contest {
    private int id;
    private String name;
    private String description;
    private LocalDate deadline;
    private String logoName;
    private String logoPath;

    public int getId() {
        return id;
    }

    public Contest setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Contest setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Contest setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public Contest setDeadline(LocalDate deadline) {
        this.deadline = deadline;
        return this;
    }

    public String getLogoName() {
        return logoName;
    }

    public Contest setLogoName(String logoName) {
        this.logoName = logoName;
        return this;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public Contest setLogoPath(String logoPath) {
        this.logoPath = logoPath;
        return this;
    }
}
