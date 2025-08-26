package me.khw7385.waitingroom.coupon.api.dto;

import me.khw7385.waitingroom.coupon.application.dto.CouponListAvailableResult;

import java.time.LocalDateTime;
import java.util.List;

public record CouponListAvailableResponse(
    List<CouponResponse> coupons
) {
    public static CouponListAvailableResponse from(CouponListAvailableResult result){
        return new CouponListAvailableResponse(
                result.coupons()
                        .stream()
                        .map(c -> new CouponResponse(c.couponIssueId(), c.couponId(), c.name(), c.expiredAt()))
                        .toList()
        );
    }

    public record CouponResponse(
        Long couponIssueId,
        Long couponId,
        String name,
        LocalDateTime expiredAt
    ){
    }
}
