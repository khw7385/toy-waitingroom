package me.khw7385.waitingroom.coupon.api;

import lombok.RequiredArgsConstructor;
import me.khw7385.waitingroom.coupon.application.CouponService;
import me.khw7385.waitingroom.coupon.application.dto.CouponIssueCommand;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService couponFacade;

    @PostMapping("/{couponId}/issues")
    @ResponseStatus(HttpStatus.CREATED)
    public void issueCoupon(
            @AuthenticationPrincipal Long memberId,
            @PathVariable Long couponId){
        couponFacade.issueCoupon(new CouponIssueCommand(memberId, couponId));
    }
}
