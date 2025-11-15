package com.example.demo.member.repository;

import com.example.demo.member.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class JpaMemberRepository implements MemberRepository{

    @PersistenceContext // JPA를 위한 영속성 컨텍스트
    private EntityManager em;

    // 회원 등록
    @Override
    public Member findByLoginId(String loginId){
        List<Member> result = em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    // 회원 저장
    @Override
    public void save(Member member){
        em.persist(member);
    }

    // 회원 목록 조회
    @Override
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 개별 회원 정보 상세 조회
    @Override
    public Member findById(Long id){
        return em.find(Member.class, id);
    }

    // 회원 정보 수정

    // 회원 삭제
    @Override
    public void deleteById(Long id){
        Member member = em.find(Member.class, id);
        em.remove(member);
    }
}
