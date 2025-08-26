package me.khw7385.waitingroom.member.application.port.outbound;

import me.khw7385.waitingroom.member.application.dto.CouponListAvailablePortResponse;

public interface CouponClientPort {
    CouponListAvailablePortResponse findAvailableCoupons(Long memberId);
}
