package me.khw7385.waitingroom.coupon.infra.persistence;

import lombok.RequiredArgsConstructor;
import me.khw7385.waitingroom.coupon.domain.CouponIssue;
import me.khw7385.waitingroom.coupon.domain.repository.CouponIssueRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponIssueRepositoryAdapter implements CouponIssueRepository {
    private final JPACouponIssueRepository jpaCouponIssueRepository;

    @Override
    public boolean existsCouponIssue(Long memberId) {
        return jpaCouponIssueRepository.existsByMemberId(memberId);
    }

    @Override
    public CouponIssue save(CouponIssue couponIssue) {
        return jpaCouponIssueRepository.save(couponIssue);
    }
}
