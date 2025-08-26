package me.khw7385.waitingroom.coupon.infra.persistence;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import me.khw7385.waitingroom.coupon.domain.dto.CouponAvailableInfo;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static me.khw7385.waitingroom.coupon.domain.QCoupon.*;
import static me.khw7385.waitingroom.coupon.domain.QCouponIssue.*;

@Repository
@RequiredArgsConstructor
public class QuerydslCouponRepository{
    private final JPAQueryFactory queryFactory;

    public List<CouponAvailableInfo> findAvailableCoupons(Long memberId, LocalDateTime expiredAt){
        return queryFactory.select(Projections.constructor(CouponAvailableInfo.class, couponIssue.id, coupon.id, coupon.name, couponIssue.expiredAt))
                .from(coupon)
                .join(couponIssue).on(coupon.id.eq(couponIssue.couponId))
                .where(eqCouponIssueMemberId(memberId), goeCouponIssueExpiredAt(expiredAt), eqCouponIssueIsUsed(false))
                .fetch();
    }

    private BooleanExpression eqCouponIssueMemberId(Long memberId){
        return memberId != null ? couponIssue.memberId.eq(memberId) : null;
    }

    private BooleanExpression goeCouponIssueExpiredAt(LocalDateTime expiredAt){
        return expiredAt != null ? couponIssue.expiredAt.goe(expiredAt) : null;
    }

    private BooleanExpression eqCouponIssueIsUsed(Boolean isUsed){
        return isUsed != null ? couponIssue.isUsed.eq(isUsed) :null;
    }
}
