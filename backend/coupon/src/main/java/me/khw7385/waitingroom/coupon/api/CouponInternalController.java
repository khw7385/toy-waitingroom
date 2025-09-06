package me.khw7385.waitingroom.coupon.api;

import lombok.RequiredArgsConstructor;
import me.khw7385.waitingroom.common.web.dto.SuccessResponse;
import me.khw7385.waitingroom.coupon.api.dto.CouponListAvailableForMemberResponse;
import me.khw7385.waitingroom.coupon.application.CouponService;
import me.khw7385.waitingroom.coupon.application.dto.CouponListAvailableForMemberResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
public class CouponInternalController {
    private final CouponService couponService;

    @GetMapping("/members/{memberId}/coupons")
    public SuccessResponse<?> getAvailableCoupons(
            @PathVariable("memberId") Long memberId
    ){
        CouponListAvailableForMemberResult result = couponService.findAvailableCoupons(memberId);

        return new SuccessResponse<>(CouponListAvailableForMemberResponse.from(result));
    }
}
