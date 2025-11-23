package com.example.demo.common.message;

public class ErrorMessage {

    // Member 관련 에러 메시지
    public static final String MEMBER_NOT_FOUND = "회원을 찾을 수 없습니다.";
    public static final String MEMBER_ALREADY_EXISTS = "이미 존재하는 로그인 아이디입니다.";
    public static final String ITEM_NOT_FOUND = "아이템을 찾을 수 없습니다.";

    // DTO 유효성 검사 에러 메시지
    public static final String LOGIN_ID_NOT_NULL = "로그인 아이디는 필수입니다.";
    public static final String LOGIN_ID_SIZE = "로그인 아이디는 4자 이상 20자 이하여야 합니다.";
    public static final String ITEM_NAME_NOT_NULL = "아이템 이름은 필수입니다.";
    public static final String PRICE_NOT_NULL = "가격은 필수입니다.";
    public static final String PRICE_RANGE = "가격은 1,000원 이상 1,000,000원 이하입니다.";
    public static final String MAX_QUANTITY = "수량은 최대 1만개까지 입니다.";
}
