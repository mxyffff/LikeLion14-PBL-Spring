package com.likelion14.PBL_Spring.member.service;

import com.likelion14.PBL_Spring.member.domain.Member;
import com.likelion14.PBL_Spring.member.domain.RoleType;
import com.likelion14.PBL_Spring.member.dto.LionCreateRequest;
import com.likelion14.PBL_Spring.member.dto.LionUpdateRequest;
import com.likelion14.PBL_Spring.member.dto.StaffCreateRequest;
import com.likelion14.PBL_Spring.member.dto.StaffUpdateRequest;
import com.likelion14.PBL_Spring.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    // Lion 등록
    @Transactional
    public Member createLion(LionCreateRequest request) {
        if (repository.existsByName(request.getName())) {
            return null;
        }

        Member lion = new Member(request.getName(), request.getMajor(), request.getGeneration(),
                request.getPart(), RoleType.LION, request.getStudentId(), null);

        return repository.save(lion);
    }

    // Staff 등록
    @Transactional
    public Member createStaff(StaffCreateRequest request) {
        if (repository.existsByName(request.getName())) {
            return null;
        }

        Member staff = new Member(request.getName(), request.getMajor(), request.getGeneration(),
                request.getPart(), RoleType.STAFF, null, request.getPosition());

        return repository.save(staff);
    }

    // 전체 Member 조회
    public List<Member> getAllMembers() {
        return repository.findAll();
    }

    // 이름으로 Member 조회
    public Member searchByName(String name) {
        return repository.findByName(name).orElse(null);
    }

    // ID로 Member 조회
    public Member findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Lion 수정
    @Transactional
    public Member updateLion(Long id, LionUpdateRequest request) {
        Member lion = repository.findById(id).orElse(null);
        if (lion == null)
            return null;

        lion.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        lion.updateStudentId(request.getStudentId());
        return repository.save(lion);
    }

    // Staff 수정
    @Transactional
    public Member updateStaff(Long id, StaffUpdateRequest request) {
        Member staff = repository.findById(id).orElse(null);
        if (staff == null)
            return null;

        staff.updateInfo(request.getMajor(), request.getGeneration(), request.getPart());
        staff.updatePosition(request.getPosition());
        return repository.save(staff);
    }

    // Member 삭제
    @Transactional
    public boolean deleteMember(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }

        repository.deleteById(id);
        return true;
    }
}
