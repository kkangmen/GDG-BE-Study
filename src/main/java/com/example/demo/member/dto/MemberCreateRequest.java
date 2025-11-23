package com.example.demo.member.dto;

import com.example.demo.common.message.ErrorMessage;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class MemberCreateRequest {

    @NotNull(message = ErrorMessage.LOGIN_ID_NOT_NULL)
    @Size(min = 4, max = 20, message = ErrorMessage.LOGIN_ID_SIZE)
    private String loginId;

    @NotNull(message = "비밀번호는 필수입니다.")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상 20자 이하여야 합니다.")
    private String password;

    @NotNull(message = "전화번호는 필수입니다.")
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "전화번호 형식이 올바르지 않습니다. 010-xxxx-xxxx")
    private String phoneNumber;

    @NotNull(message = "주소는 필수입니다.")
    private String address;

    public MemberCreateRequest(String loginId, String password, String phoneNumber, String address) {
        this.loginId = loginId;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
