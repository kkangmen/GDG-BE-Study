package com.example.demo.member;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MemberRepository {

    private static final HashMap<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    /**
     * 회원 저장
     *
     * @param member 저장할 회원 객체
     * @return 저장된 회원 객체 (ID가 할당된 상태)
     */
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    /**
     * ID로 회원 조회
     *
     * @param id 조회할 회원의 ID
     * @return Member - 회원이 있으면 Member 객체, 없으면 null 반환
     */
    public Member findById(Long id){
        return store.get(id);
    }
    /**
     * 전체 회원 조회
     *
     * @return 모든 회원의 리스트
     */
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    /**
     * 로그인 아이디로 회원 조회
     *
     * @param loginId 조회할 로그인 아이디
     * @return member - 해당 로그인 아이디를 가진 회원, 없으면 null 반환
     */
    public Member findByLoginId(String loginId){
        for (Member member : store.values()){
            if (member.getLoginId().equals(loginId)){
                return member;
            }
        }
        return null;
    }

    /**
     * 회원 삭제
     *
     * @param id 삭제할 회원의 ID
     */
    public void deleteById(Long id){
        store.remove(id);
    }

    /**
     * 전체 회원 삭제 (테스트용)
     * - 데이터 초기화할 때 사용
     */
    public void deleteAll(){
        store.clear();
    }

    /**
     * 회원 수 조회 (테스트용)
     *
     * @return 저장된 회원의 수
     */
    public long count(){
        return store.size();
    }
}
