package me.khw7385.waitingroom.coupon.infra.persistence;

import jakarta.persistence.LockModeType;
import me.khw7385.waitingroom.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JPACouponRepository extends JpaRepository<Coupon, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT c FROM Coupon c WHERE c.id = :id")
    Optional<Coupon> findByIdForUpdate(Long id);

    List<Coupon> findByValidUntilIsAfter(LocalDateTime validUntil);
}
