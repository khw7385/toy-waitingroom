package me.khw7385.waitingroom.member.api.dto;

import me.khw7385.waitingroom.member.application.dto.CouponListAvailablePortResponse;
import me.khw7385.waitingroom.member.application.dto.MemberCouponListResult;

import java.time.LocalDateTime;
import java.util.List;

public record MemberCouponListResponse(
        List<MemberCouponResponse> coupons
) {
    public static MemberCouponListResponse from(MemberCouponListResult result){
        return new MemberCouponListResponse(
                result.coupons().stream()
                        .map(c -> new MemberCouponResponse(c.couponIssueId(), c.couponId(), c.name(), c.expiredAt()))
                        .toList()
        );
    }

    public record MemberCouponResponse(
            Long couponIssueId,
            Long couponId,
            String name,
            LocalDateTime expiredAt
    ){
    }
}
