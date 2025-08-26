package me.khw7385.waitingroom.coupon.infra.persistence;

import lombok.RequiredArgsConstructor;
import me.khw7385.waitingroom.coupon.domain.Coupon;
import me.khw7385.waitingroom.coupon.domain.dto.CouponAvailableInfo;
import me.khw7385.waitingroom.coupon.domain.repository.CouponRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CouponRepositoryAdapter implements CouponRepository {
    private final JPACouponRepository jpaCouponRepository;
    private final QuerydslCouponRepository querydslCouponRepository;

    @Override
    public Optional<Coupon> findCouponForIssue(Long id) {
        return jpaCouponRepository.findByIdForUpdate(id);
    }

    @Override
    public List<CouponAvailableInfo> findAvailableCoupons(Long memberId, LocalDateTime expiredAt) {
        return querydslCouponRepository.findAvailableCoupons(memberId, expiredAt);
    }

    @Override
    public Coupon save(Coupon coupon) {
        return jpaCouponRepository.save(coupon);
    }
}
