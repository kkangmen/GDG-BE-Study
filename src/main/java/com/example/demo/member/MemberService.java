package com.example.demo.member;

import com.example.demo.member.dto.MemberCreateRequest;
import com.example.demo.member.dto.MemberUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    public final MemberRepository memberRepository;

    // 회원 등록
    @Transactional
    public Long createMember(MemberCreateRequest request){
        Member exisitingMember = memberRepository.findByLoginId(request.getLoginId());
        if (exisitingMember != null){
            throw new RuntimeException("이미 존재하는 로그인 아이디입니다." + request.getLoginId());
        }

        Member member = new Member(
                request.getLoginId(),
                request.getPassword(),
                request.getPhoneNumber(),
                request.getAddress());
        memberRepository.save(member);
        return member.getId();
    }

    // 회원 목록 조회
    @Transactional
    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

    // 개별 회원 정보 상세 조회
    @Transactional
    public Member getMember(Long id){
        if (memberRepository.findById(id) == null){
            throw new RuntimeException("회원이 존재하지 않습니다.");
        }
        return memberRepository.findById(id);
    }

    // 회원 정보 수정
    @Transactional
    public void updateMember(Long id, MemberUpdateRequest request){
        Member member = memberRepository.findById(id);

        if (member == null){
            throw new RuntimeException("회원을 찾을 수 없습니다.");
        }

        member.updateInfo(request.getPassword(), request.getPhoneNumber(), request.getAddress());
    }

    // 회원 삭제
    @Transactional
    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }

}
