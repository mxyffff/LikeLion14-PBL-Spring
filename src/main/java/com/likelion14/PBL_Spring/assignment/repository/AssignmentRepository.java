package com.likelion14.PBL_Spring.assignment.repository;

import com.likelion14.PBL_Spring.assignment.domain.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByMemberId(Long memberId); // 멤버별 과제 목록 조회
}
