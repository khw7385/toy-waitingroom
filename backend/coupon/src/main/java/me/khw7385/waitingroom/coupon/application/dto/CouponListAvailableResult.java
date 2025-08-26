package me.khw7385.waitingroom.coupon.application.dto;

import me.khw7385.waitingroom.coupon.domain.dto.CouponAvailableInfo;

import java.time.LocalDateTime;
import java.util.List;

public record CouponListAvailableResult(
    List<CouponAvailableResult> coupons
) {
    public static CouponListAvailableResult from(List<CouponAvailableInfo> infos){
        return new CouponListAvailableResult(
                infos.stream()
                        .map(i -> new CouponAvailableResult(i.couponIssueId(), i.couponId(), i.name(), i.expiredAt()))
                        .toList()
        );
    }

    public record CouponAvailableResult(
        Long couponIssueId,
        Long couponId,
        String name,
        LocalDateTime expiredAt
    ){
    }
}
