package com.likelion14.PBL_Spring.assignment.service;

import com.likelion14.PBL_Spring.assignment.domain.Assignment;
import com.likelion14.PBL_Spring.assignment.dto.AssignmentCreateRequest;
import com.likelion14.PBL_Spring.assignment.dto.AssignmentUpdateRequest;
import com.likelion14.PBL_Spring.assignment.repository.AssignmentRepository;
import com.likelion14.PBL_Spring.global.exception.AssignmentNotFoundException;
import com.likelion14.PBL_Spring.global.exception.MemberNotFoundException;
import com.likelion14.PBL_Spring.member.domain.Member;
import com.likelion14.PBL_Spring.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 모든 메서드가 기본적으로 읽기 전용 트랜잭션으로 동작 - JPA가 변경 감지 수행 X
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final MemberRepository memberRepository;

    // 생성자 주입
    public AssignmentService(
            AssignmentRepository assignmentRepository, MemberRepository memberRepository
    ) {
        this.assignmentRepository = assignmentRepository;
        this.memberRepository = memberRepository;
    }

    // 과제 등록
    @Transactional
    public Assignment create(Long memberId, AssignmentCreateRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("해당 멤버를 찾을 수 없습니다. id: "+ memberId));

        Assignment assignment = new Assignment(
                request.getTitle(), request.getDescription(), member
        );

        return assignmentRepository.save(assignment);
    }

    // 전체 조회
    public List<Assignment> findAll() {
        return assignmentRepository.findAll();
    }

    // 단건 조회
    public Assignment findById(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException("해당 과제를 찾을 수 없습니다. id: " + id));
    }

    // 과제 제목 검색
    public List<Assignment> searchByTitle(String keyword) {
        return assignmentRepository.findByTitleContaining(keyword);
    }

    // 멤버별 과제 조회
    public List<Assignment> findByMemberId(Long memberId) {
        return assignmentRepository.findByMemberId(memberId);
    }

    // 과제 수정
    @Transactional
    public Assignment update(Long id, AssignmentUpdateRequest request) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new AssignmentNotFoundException("해당 과제를 찾을 수 없습니다. id: " + id));

        assignment.updateInfo(request.getTitle(), request.getDescription());

        return assignmentRepository.save(assignment);
    }

    // 과제 삭제
    @Transactional
    public void delete(Long id) {
        if (!assignmentRepository.existsById(id)) {
            throw new AssignmentNotFoundException("해당 과제를 찾을 수 없습니다. id: " + id);
        }

        assignmentRepository.deleteById(id);
    }

}
