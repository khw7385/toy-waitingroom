package me.khw7385.waitingroom.coupon.application.dto;

import me.khw7385.waitingroom.coupon.domain.dto.CouponAvailableInfo;

import java.time.LocalDateTime;
import java.util.List;

public record CouponListAvailableForMemberResult(
    List<CouponAvailableForMemberResult> coupons
) {
    public static CouponListAvailableForMemberResult from(List<CouponAvailableInfo> infos){
        return new CouponListAvailableForMemberResult(
                infos.stream()
                        .map(i -> new CouponAvailableForMemberResult(i.couponIssueId(), i.couponId(), i.name(), i.expiredAt()))
                        .toList()
        );
    }

    public record CouponAvailableForMemberResult(
        Long couponIssueId,
        Long couponId,
        String name,
        LocalDateTime expiredAt
    ){
    }
}
