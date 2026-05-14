package com.likelion14.PBL_Spring.member.dto;

// Lion 등록 요청 DTO
public class LionCreateRequest {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String studentId;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }

    public int getGeneration() { return generation; }
    public void setGeneration(int generation) { this.generation = generation; }

    public String getPart() { return part; }
    public void setPart(String part) { this.part = part; }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
}
