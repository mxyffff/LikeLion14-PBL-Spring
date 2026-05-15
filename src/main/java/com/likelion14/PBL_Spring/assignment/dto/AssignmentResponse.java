package com.likelion14.PBL_Spring.assignment.dto;

import com.likelion14.PBL_Spring.assignment.domain.Assignment;

public class AssignmentResponse {
    private Long id;
    private String title;
    private String description;
    private Long memberId;
    private String memberName;

    public static AssignmentResponse from(Assignment assignment) {
        AssignmentResponse response = new AssignmentResponse();

        response.id = assignment.getId();
        response.title = assignment.getTitle();
        response.description = assignment.getDescription();
        response.memberId = assignment.getMember().getId();
        response.memberName = assignment.getMember().getName();

        return response;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Long getMemberId() { return memberId; }
    public String getMemberName() { return memberName; }
}
