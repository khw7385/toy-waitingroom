package me.khw7385.waitingroom.coupon.application;

import lombok.RequiredArgsConstructor;
import me.khw7385.waitingroom.coupon.application.dto.CouponIssueCommand;
import me.khw7385.waitingroom.coupon.application.dto.CouponListAvailableForMemberResult;
import me.khw7385.waitingroom.coupon.application.dto.CouponListAvailableResult;
import me.khw7385.waitingroom.coupon.core.exception.CouponAlreadyIssuedException;
import me.khw7385.waitingroom.coupon.core.exception.CouponNotFoundException;
import me.khw7385.waitingroom.coupon.domain.Coupon;
import me.khw7385.waitingroom.coupon.domain.CouponIssue;
import me.khw7385.waitingroom.coupon.domain.repository.CouponIssueRepository;
import me.khw7385.waitingroom.coupon.domain.repository.CouponRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;
    private final CouponIssueRepository couponIssueRepository;

    @Transactional
    public void issueCoupon(CouponIssueCommand command){
        Coupon coupon = couponRepository.findCouponForIssue(command.couponId()).orElseThrow(CouponNotFoundException::new);
        boolean hasCoupon = couponIssueRepository.existsCouponIssue(command.memberId());

        if(hasCoupon){
            throw new CouponAlreadyIssuedException();
        }
        coupon.issue();
        couponIssueRepository.save(CouponIssue.create(coupon.getId(), command.memberId(), coupon.getValidUntil()));
    }

    @Transactional(readOnly = true)
    public CouponListAvailableResult findCoupons(){
        List<Coupon> coupons = couponRepository.findAllCoupons(LocalDateTime.now());

        return CouponListAvailableResult.from(coupons);
    }

    @Transactional(readOnly = true)
    public CouponListAvailableForMemberResult findAvailableCoupons(Long memberId){
        return CouponListAvailableForMemberResult.from(couponRepository.findAvailableCoupons(memberId, LocalDateTime.now()));
    }
}
