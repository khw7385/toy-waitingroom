package me.khw7385.waitingroom.member.application;

import lombok.RequiredArgsConstructor;
import me.khw7385.waitingroom.member.application.dto.CouponListAvailablePortResponse;
import me.khw7385.waitingroom.member.application.dto.MemberCouponListResult;
import me.khw7385.waitingroom.member.application.port.outbound.CouponClientPort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final CouponClientPort couponClientPort;

    public MemberCouponListResult findMemberCoupons(Long memberId){
        CouponListAvailablePortResponse response = couponClientPort.findAvailableCoupons(memberId);
        return MemberCouponListResult.from(response);
    }
}
