package com.likelion14.PBL_Spring.member.controller;

import com.likelion14.PBL_Spring.member.domian.role.Lion;
import com.likelion14.PBL_Spring.member.domian.role.Role;
import com.likelion14.PBL_Spring.member.domian.role.Staff;
import com.likelion14.PBL_Spring.member.dto.*;
import com.likelion14.PBL_Spring.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // Lion 등록
    @PostMapping("/lions")
    public ResponseEntity<LionResponse> createLion(@RequestBody LionCreateRequest request) {
        Role lion = memberService.createLion(request);

        if (lion == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 상태코드 409
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(LionResponse.from((Lion) lion));
    }

    // Staff 등록
    @PostMapping("/staffs")
    public ResponseEntity<StaffResponse> createStaff(@RequestBody StaffCreateRequest request) {
        Role staff = memberService.createStaff(request);

        if (staff == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 상태코드 409
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(StaffResponse.from((Staff) staff));
    }

    // member 조회
    @GetMapping("/{name}")
    public ResponseEntity<?> getMember(@PathVariable String name) {
        Role member = memberService.searchByName(name);

        if (member == null) {
            return ResponseEntity.notFound().build(); // 404 반환
        }

        return ResponseEntity.ok(toResponse(member));
    }

    // Lion 수정
    @PutMapping("/lions/{name}")
    public ResponseEntity<LionResponse> updateLion(@PathVariable String name, @RequestBody LionUpdateRequest request) {
        Role lion = memberService.updateLion(name, request);

        if (lion == null) {
            return ResponseEntity.notFound().build(); // 404 반환
        }

        return ResponseEntity.ok(LionResponse.from((Lion) lion));
    }

    // Staff 수정
    @PutMapping("/staffs/{name}")
    public ResponseEntity<StaffResponse> updateStaff(@PathVariable String name, @RequestBody StaffUpdateRequest request) {
        Role staff = memberService.updateStaff(name, request);

        if (staff == null) {
            return ResponseEntity.notFound().build(); // 404 반환
        }

        return ResponseEntity.ok(StaffResponse.from((Staff) staff));
    }

    // member 삭제
    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteMember(@PathVariable String name) {
        boolean success = memberService.deleteMember(name);

        if (!success) {
            return ResponseEntity.notFound().build(); // 404 반환
        }
        return ResponseEntity.noContent().build();
    }

    private Object toResponse(Role role) {
        if (role instanceof Lion lion) {
            return LionResponse.from(lion);
        } else if (role instanceof Staff staff) {
            return StaffResponse.from(staff);
        }

        return role;
    }
}
