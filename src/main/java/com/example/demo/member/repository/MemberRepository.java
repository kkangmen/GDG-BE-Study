package com.example.demo.member.repository;

import com.example.demo.member.entity.Member;

import java.util.List;

public interface MemberRepository {
    Member findByLoginId(String loginId);
    void save(Member member);
    List<Member> findAll();
    Member findById(Long id);
    void deleteById(Long id);
}
