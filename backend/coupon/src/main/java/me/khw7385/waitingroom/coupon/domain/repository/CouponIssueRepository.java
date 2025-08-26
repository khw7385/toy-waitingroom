package me.khw7385.waitingroom.coupon.domain.repository;

import me.khw7385.waitingroom.coupon.domain.CouponIssue;

public interface CouponIssueRepository{
    boolean existsCouponIssue(Long memberId);
    CouponIssue save(CouponIssue couponIssue);
}
