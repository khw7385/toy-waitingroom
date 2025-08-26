package me.khw7385.waitingroom.coupon.infra.persistence;

import me.khw7385.waitingroom.coupon.domain.CouponIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPACouponIssueRepository extends JpaRepository<CouponIssue, Long> {
    boolean existsByMemberId(Long memberId);
}
