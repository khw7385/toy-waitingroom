package me.khw7385.waitingroom.common.web.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    // 토큰 인증 관련
    TOKEN_REQUIRED("요청 헤더에 토큰이 존재하지 않습니다."),
    TOKEN_EXPIRED("토큰이 만료되었습니다."),
    TOKEN_INVALID("타당하지 않은 토큰입니다."),

    // Member
    DUPLICATE_LOGIN_ID("이미 존재하는 로그인 ID 입니다."),
    NOT_FOUND_LOGIN_ID("존재하지 않은 로그인 ID 입니다."),
    NOT_MATCH_LOGIN_ID_AND_PASSWORD("로그인 ID 와 비밀번호가 일치하지 않습니다."),

    // Coupon
    COUPON_NOT_FOUND("해당 쿠폰이 존재하지 않습니다."),
    COUPON_ALREADY_ISSUED("이미 발급받은 쿠폰입니다."),
    COUPON_OUT_OF_STOCK("쿠폰이 모두 소진되었습니다.");

    String meesage;

    ErrorCode(String message) {
        this.meesage = message;
    }
}
