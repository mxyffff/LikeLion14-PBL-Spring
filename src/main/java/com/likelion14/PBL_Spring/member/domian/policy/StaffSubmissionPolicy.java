package com.likelion14.PBL_Spring.member.domian.policy;

public class StaffSubmissionPolicy implements SubmissionPolicy {
    @Override
    public boolean canSubmit() {
        return false;
    }
}