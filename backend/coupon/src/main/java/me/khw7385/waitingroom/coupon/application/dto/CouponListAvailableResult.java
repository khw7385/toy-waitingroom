package me.khw7385.waitingroom.coupon.application.dto;

import lombok.Builder;
import me.khw7385.waitingroom.coupon.domain.Coupon;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CouponListAvailableResult(
        List<CouponAvailableResult> coupons
) {
    public static CouponListAvailableResult from(List<Coupon> coupons){

        return CouponListAvailableResult.builder()
                .coupons(
                        coupons.stream()
                                .map(coupon -> new CouponAvailableResult(coupon.getId(), coupon.getName(), coupon.getValidUntil()))
                                .toList()
                )
                .build();
    }

    public record CouponAvailableResult(
            Long couponId,
            String name,
            LocalDateTime expiredAt
    ){
    }
}
