package com.likelion14.PBL_Spring.member.repository;

import com.likelion14.PBL_Spring.member.domian.role.Role;

import java.util.List;

// 멤버 저장소 인터페이스
public interface MemberRepository {
    void save(Role member);
    Role findByName(String name);
    List<Role> findAll();
    boolean existsByName(String name);
}
