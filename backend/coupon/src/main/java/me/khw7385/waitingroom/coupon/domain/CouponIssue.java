package me.khw7385.waitingroom.coupon.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import me.khw7385.waitingroom.common.data.domain.BaseTimeEntity;

import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"coupon_id", "member_id"})})
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CouponIssue extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_issue_id")
    private Long id;

    private Long couponId;
    private Long memberId;

    private Boolean isUsed;
    private LocalDateTime expiredAt;

    public static CouponIssue create(Long couponId, Long memberId, LocalDateTime expiredAt){
        return CouponIssue.builder()
                .couponId(couponId)
                .memberId(memberId)
                .expiredAt(expiredAt)
                .build();
    }
}
