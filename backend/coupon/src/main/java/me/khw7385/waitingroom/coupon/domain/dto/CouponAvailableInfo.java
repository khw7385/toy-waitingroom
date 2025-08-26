package me.khw7385.waitingroom.coupon.domain.dto;

import java.time.LocalDateTime;

public record CouponAvailableInfo(
        Long couponIssueId,
        Long couponId,
        String name,
        LocalDateTime expiredAt
) {
}
