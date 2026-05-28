package com.likelion14.PBL_Spring.member.repository;

import com.likelion14.PBL_Spring.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// 멤버 저장소 인터페이스
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);

    boolean existsByName(String name);

    List<Member> findByPart(String part);
}
