package me.khw7385.waitingroom.member.application.dto;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public record CouponListAvailablePortResponse(
    List<CouponPortResponse> coupons
) {
    public static CouponListAvailablePortResponse empty(){
        return new CouponListAvailablePortResponse(Collections.emptyList());
    }

    public record CouponPortResponse(
            Long couponIssueId,
            Long couponId,
            String name,
            LocalDateTime expiredAt
    ){
    }
}
