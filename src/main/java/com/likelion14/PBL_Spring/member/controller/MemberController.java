package com.likelion14.PBL_Spring.member.controller;

import com.likelion14.PBL_Spring.member.domain.Member;
import com.likelion14.PBL_Spring.member.dto.*;
import com.likelion14.PBL_Spring.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Member", description = "멤버 관리 API")
@RestController
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // Lion 등록
    @Operation(summary = "Lion(아기사자) 등록")
    @PostMapping("/lions")
    public ResponseEntity<MemberResponse> createLion(@RequestBody LionCreateRequest request) {
        Member lion = memberService.createLion(request);

        if (lion == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 상태코드 409
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(MemberResponse.from(lion));
    }

    // Staff 등록
    @Operation(summary = "Staff(운영진) 등록")
    @PostMapping("/staffs")
    public ResponseEntity<MemberResponse> createStaff(@RequestBody StaffCreateRequest request) {
        Member staff = memberService.createStaff(request);

        if (staff == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 상태코드 409
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(MemberResponse.from(staff));
    }

    // 전체 member 조회
    @Operation(summary = "전체 멤버 검색")
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        List<MemberResponse> responses = memberService.getAllMembers().stream()
                .map(MemberResponse::from)
                .toList();

        return ResponseEntity.ok(responses);
    }

    // member id로 단건 조회
    @Operation(summary = "id로 멤버 검색")
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable Long id) {
        Member member = memberService.findById(id);

        if (member == null) {
            return ResponseEntity.notFound().build(); // 404 반환
        }

        return ResponseEntity.ok(MemberResponse.from(member));
    }

    // member 이름으로 단건 조회
    @Operation(summary = "이름으로 멤버 검색")
    @GetMapping("/search")
    public ResponseEntity<?> searchByName(@RequestParam("name") String name) {
        Member member = memberService.searchByName(name);

        if (member == null) {
            return ResponseEntity.notFound().build(); // 404 반환
        }

        return ResponseEntity.ok(MemberResponse.from(member));
    }

    // Lion 수정
    @Operation(summary = "Lion 정보 수정")
    @PutMapping("/lions/{id}")
    public ResponseEntity<MemberResponse> updateLion(@PathVariable Long id, @RequestBody LionUpdateRequest request) {
        Member lion = memberService.updateLion(id, request);

        if (lion == null) {
            return ResponseEntity.notFound().build(); // 404 반환
        }

        return ResponseEntity.ok(MemberResponse.from(lion));
    }

    // Staff 수정
    @Operation(summary = "Staff 정보 수정")
    @PutMapping("/staffs/{id}")
    public ResponseEntity<MemberResponse> updateStaff(@PathVariable Long id, @RequestBody StaffUpdateRequest request) {
        Member staff = memberService.updateStaff(id, request);

        if (staff == null) {
            return ResponseEntity.notFound().build(); // 404 반환
        }

        return ResponseEntity.ok(MemberResponse.from(staff));
    }

    // member 삭제
    @Operation(summary = "멤버 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        boolean success = memberService.deleteMember(id);

        if (!success) {
            return ResponseEntity.notFound().build(); // 404 반환
        }
        return ResponseEntity.noContent().build();
    }
}
