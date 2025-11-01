package com.example.demo.member;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext // JPA를 위한 영속성 컨텍스트
    private EntityManager em;

    // 회원 등록
    public Member findByLoginId(String loginId){
        List<Member> result = em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    // 회원 저장
    public void save(Member member){
        em.persist(member);
    }

    // 회원 목록 조회
    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    // 개별 회원 정보 상세 조회
    public Member findById(Long id){
        return em.find(Member.class, id);
    }

    // 회원 정보 수정

    // 회원 삭제
    public void deleteById(Long id){
        Member member = em.find(Member.class, id);
        em.remove(member);
    }
}
