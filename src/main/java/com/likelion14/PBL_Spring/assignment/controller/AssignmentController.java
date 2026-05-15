package com.likelion14.PBL_Spring.assignment.controller;

import com.likelion14.PBL_Spring.assignment.domain.Assignment;
import com.likelion14.PBL_Spring.assignment.dto.AssignmentCreateRequest;
import com.likelion14.PBL_Spring.assignment.dto.AssignmentResponse;
import com.likelion14.PBL_Spring.assignment.dto.AssignmentUpdateRequest;
import com.likelion14.PBL_Spring.assignment.service.AssignmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssignmentController {
    private final AssignmentService assignmentService;

    public AssignmentController(AssignmentService assignmentService) { // 생성자 주입
        this.assignmentService = assignmentService;
    }

    // 과제 등록
    @PostMapping("/members/{memberId}/assignments")
    public ResponseEntity<AssignmentResponse> create(@PathVariable Long memberId, @RequestBody AssignmentCreateRequest request) {
        Assignment assignment = assignmentService.create(memberId, request);

        if (assignment == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(AssignmentResponse.from(assignment));
    }

    // 과제 단건 조회
    @GetMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> findById(@PathVariable Long id) {
        Assignment assignment = assignmentService.findById(id);

        if (assignment == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(AssignmentResponse.from(assignment));
    }

    // 멤버별 과제 목록 조회
    @GetMapping("/members/{memberId}/assignments")
    public ResponseEntity<List<AssignmentResponse>> findByMemberId(@PathVariable Long memberId) {
        List<AssignmentResponse> responses = assignmentService.findByMemberId(memberId).stream()
                .map(AssignmentResponse::from).toList();

        return ResponseEntity.ok().body(responses);
    }

    // 과제 수정
    @PutMapping("/assignments/{id}")
    public ResponseEntity<AssignmentResponse> update(@PathVariable Long id, @RequestBody AssignmentUpdateRequest request) {
        Assignment assignment = assignmentService.update(id, request);

        if (assignment == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(AssignmentResponse.from(assignment));
    }

    // 과제 삭제
    @DeleteMapping("/assignments/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean success = assignmentService.delete(id);

        if (!success) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
