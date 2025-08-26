package me.khw7385.waitingroom.coupon.application.dto;

public record CouponIssueCommand(
        Long memberId,
        Long couponId
) {
}
