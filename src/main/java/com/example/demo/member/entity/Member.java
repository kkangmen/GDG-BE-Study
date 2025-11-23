package com.example.demo.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity //저장되고 관리되는 데이터임을 명시
@Table(name = "members")
@NoArgsConstructor // 파라미터가 없는 디폴트 생성자를 자동으로 생성
//public Member(){
//}
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_loginid", length = 20)
    private String loginId;

    @Column(name = "member_pw", length = 100)
    private String password;

    @Column(name = "member_phone", length = 20)
    private String phoneNumber;

    @Column(name = "member_address", length = 100)
    private String address;

    @Column(name = "member_point")
    private int point;

    public Member(String loginId, String password, String phoneNumber, String address) {
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.point = 0;
    }

    public void updateInfo(String password, String phoneNumber, String address){
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
