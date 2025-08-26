package me.khw7385.waitingroom.coupon.domain;

import jakarta.persistence.*;
import lombok.*;
import me.khw7385.waitingroom.common.data.domain.BaseTimeEntity;
import me.khw7385.waitingroom.coupon.core.exception.CouponOutOfStockException;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Coupon extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long id;

    private String name;
    private Integer quantity;

    private LocalDateTime validUntil;

    public void issue(){
        if (this.quantity <= 0){
            throw new CouponOutOfStockException();
        }
        this.quantity--;
    }
}
