package com.likelion14.PBL_Spring.member.domian.role;

import com.likelion14.PBL_Spring.member.domian.policy.LionSubmissionPolicy;
import com.likelion14.PBL_Spring.member.domian.policy.SubmissionPolicy;

public class Lion extends Role {
    private String studentId;

    @Override
    public SubmissionPolicy submissionPolicy() {
        return new LionSubmissionPolicy();
    }

    public Lion(String name, String major, int generation, String part, String studentId) {
        super(name, major, generation, part);
        this.studentId = studentId;
    }
    public String getStudentId() {
        return studentId;
    }

    @Override
    public String getInfo() {
        return "이름: " + getName() + ", 전공: " + getMajor() + ", 기수: " + getGeneration() + ", 파트: " + getPart() + "\n학번: " + studentId;
    }

    @Override
    public String roleName() {
        return "아기사자";
    }
}
