package com.example.demo.member.service;

import com.example.demo.common.exception.BadRequestException;
import com.example.demo.common.exception.NotFoundException;
import com.example.demo.common.message.ErrorMessage;
import com.example.demo.member.entity.Member;
import com.example.demo.member.dto.MemberCreateRequest;
import com.example.demo.member.dto.MemberUpdateRequest;
import com.example.demo.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    // 회원 등록
    @Transactional
    @Override
    public Long createMember(MemberCreateRequest request){
        Member exisitingMember = memberRepository.findByLoginId(request.getLoginId());
        if (exisitingMember != null){
            throw new BadRequestException(ErrorMessage.MEMBER_ALREADY_EXISTS);
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
    @Override
    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

    // 개별 회원 정보 상세 조회
    @Transactional
    @Override
    public Member getMember(Long id){
        if (memberRepository.findById(id) == null){
            throw new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND);
        }
        return memberRepository.findById(id);
    }

    // 회원 정보 수정
    @Transactional
    @Override
    public void updateMember(Long id, MemberUpdateRequest request){
        Member member = memberRepository.findById(id);

        if (member == null){
            throw new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND);
        }

        String password = (request.getPassword() != null) ? request.getPassword() : member.getPassword();
        String phoneNumber = (request.getPhoneNumber() != null) ? request.getPhoneNumber() : member.getPhoneNumber();
        String address = (request.getAddress() != null) ? request.getAddress() : member.getAddress();

        member.updateInfo(password, phoneNumber, address);
    }

    // 회원 삭제
    @Transactional
    @Override
    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }
}
