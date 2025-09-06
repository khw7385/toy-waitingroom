package me.khw7385.waitingroom.coupon.api.dto;

import me.khw7385.waitingroom.coupon.application.dto.CouponListAvailableForMemberResult;

import java.time.LocalDateTime;
import java.util.List;

public record CouponListAvailableForMemberResponse(
    List<CouponAvailableForMemberResponse> coupons
) {
    public static CouponListAvailableForMemberResponse from(CouponListAvailableForMemberResult result){
        return new CouponListAvailableForMemberResponse(
                result.coupons()
                        .stream()
                        .map(c -> new CouponAvailableForMemberResponse(c.couponIssueId(), c.couponId(), c.name(), c.expiredAt()))
                        .toList()
        );
    }

    public record CouponAvailableForMemberResponse(
        Long couponIssueId,
        Long couponId,
        String name,
        LocalDateTime expiredAt
    ){
    }
}
