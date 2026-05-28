package com.likelion14.PBL_Spring.assignment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class AssignmentCreateRequest {
    private String title;
    private String description;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
