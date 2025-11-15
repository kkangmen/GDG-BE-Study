package com.example.demo.member.controller;

import com.example.demo.member.entity.Member;
import com.example.demo.member.service.MemberService;
import com.example.demo.member.dto.MemberCreateRequest;
import com.example.demo.member.dto.MemberUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // 회원 생성
    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberCreateRequest request){
        Long memberId = memberService.createMember(request);
        return ResponseEntity.created(URI.create("/members/" + memberId)).build();
    }

    // 회원 정보 조회
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers(){
        List<Member> memberList = memberService.getAllMembers();
        return ResponseEntity.ok(memberList);
    }

    // 특정 회원 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMember(@PathVariable Long memberId){
        Member member = memberService.getMember(memberId);
        return ResponseEntity.ok(member);
    }

    // 특정 회원 수정
    @PatchMapping("/{memberId}")
    public ResponseEntity<Void> updateMember(@PathVariable Long memberId,
                                               @RequestBody MemberUpdateRequest request){
        memberService.updateMember(memberId, request);
        return ResponseEntity.ok().build();
    }

    // 특정 회원 삭제
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId){
        memberService.deleteMember(memberId);
        return ResponseEntity.ok().build();
    }
}
