package me.khw7385.waitingroom.coupon.api.dto;

import lombok.Builder;
import me.khw7385.waitingroom.coupon.application.dto.CouponListAvailableResult;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CouponListAvailableResponse(
    List<CouponAvailableResponse> coupons
) {
    public static CouponListAvailableResponse from(CouponListAvailableResult result){
        return CouponListAvailableResponse.builder()
                .coupons(
                        result.coupons()
                                .stream()
                                .map(coupon -> new CouponAvailableResponse(coupon.couponId(), coupon.name(), coupon.expiredAt()))
                                .toList()
                )
                .build();
    }

    public record CouponAvailableResponse(
            Long couponId,
            String name,
            LocalDateTime expiredAt
    ){
    }
}
