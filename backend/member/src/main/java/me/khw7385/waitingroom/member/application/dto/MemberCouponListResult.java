package me.khw7385.waitingroom.member.application.dto;

import java.time.LocalDateTime;
import java.util.List;

public record MemberCouponListResult(
        List<CouponResult> coupons
) {
    public static MemberCouponListResult from(CouponListAvailablePortResponse portResponse){
        return new MemberCouponListResult(
                portResponse.coupons().stream()
                        .map(c -> new CouponResult(c.couponIssueId(), c.couponId(), c.name(), c.expiredAt()))
                        .toList()
        );
    }

    public record CouponResult(
            Long couponIssueId,
            Long couponId,
            String name,
            LocalDateTime expiredAt
    ){
    }
}
