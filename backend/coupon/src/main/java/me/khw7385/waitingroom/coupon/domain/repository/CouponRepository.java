package me.khw7385.waitingroom.coupon.domain.repository;

import me.khw7385.waitingroom.coupon.domain.Coupon;
import me.khw7385.waitingroom.coupon.domain.dto.CouponAvailableInfo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CouponRepository{
    Optional<Coupon> findCouponForIssue(Long id);

    List<Coupon> findAllCoupons(LocalDateTime expiredAt);
    List<CouponAvailableInfo> findAvailableCoupons(Long memberId, LocalDateTime expiredAt);

    Coupon save(Coupon coupon);
}
