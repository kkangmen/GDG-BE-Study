package com.example.demo.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // 파라미터가 없는 디폴트 생성자를 자동으로 생성
//public Member(){
//}
public class Member {

    private Long id;
    private String loginId;
    private String password;
    private String phoneNumber;
    private String address;
    private int point;

    public Member(String loginId, String password, String phoneNumber, String address) {
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.point = 0;
    }

    public void updateInfo(String password, String phoneNumber, String address){
        if (password != null){
            this.password = password;
        }
        if (phoneNumber != null){
            this.phoneNumber = phoneNumber;
        }
        if (address != null){
            this.address = address;
        }
    }
}
