package com.likelion14.PBL_Spring.member.domian.role;

import com.likelion14.PBL_Spring.member.domian.policy.StaffSubmissionPolicy;
import com.likelion14.PBL_Spring.member.domian.policy.SubmissionPolicy;

public class Staff extends Role {
    private String position;

    @Override
    public SubmissionPolicy submissionPolicy() {
        return new StaffSubmissionPolicy();
    }

    public Staff(String name, String major, int generation, String part, String position) {
        super(name, major, generation, part);
        this.position = position;
    }
    public String getPosition() {
        return position;
    }

    @Override
    public String getInfo() {
        return "이름: " + getName() + ", 전공: " + getMajor() + ", 기수: " + getGeneration() + ", 파트: " + getPart() + "\n직책: " + position;
    }

    @Override
    public String roleName() {
        return "운영진";
    }
}
