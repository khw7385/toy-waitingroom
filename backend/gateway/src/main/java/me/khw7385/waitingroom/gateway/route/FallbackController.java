package me.khw7385.waitingroom.gateway.route;

import me.khw7385.waitingroom.common.web.exception.ServiceUnavailableException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    private static final String MEMBER_SERVICE = "회원";
    private static final String COUPON_SERVICE = "쿠폰";

    @RequestMapping("/member-service")
    public void fallbackMemberService(){
        throw new ServiceUnavailableException(MEMBER_SERVICE);
    }

    @RequestMapping("/coupon-service")
    public void fallbackCouponService(){
        throw new ServiceUnavailableException(COUPON_SERVICE);
    }
}
