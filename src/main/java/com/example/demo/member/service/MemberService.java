package com.example.demo.member.service;

import com.example.demo.member.dto.MemberCreateRequest;
import com.example.demo.member.dto.MemberUpdateRequest;
import com.example.demo.member.entity.Member;

import java.util.List;

public interface MemberService {
    Long createMember(MemberCreateRequest request);
    List<Member> getAllMembers();
    Member getMember(Long id);
    void updateMember(Long id, MemberUpdateRequest request);
    void deleteMember(Long id);
}
