package me.khw7385.waitingroom.coupon.unit.application;

import me.khw7385.waitingroom.coupon.application.CouponService;
import me.khw7385.waitingroom.coupon.application.dto.CouponIssueCommand;
import me.khw7385.waitingroom.coupon.application.dto.CouponListAvailableResult;
import me.khw7385.waitingroom.coupon.core.exception.CouponAlreadyIssuedException;
import me.khw7385.waitingroom.coupon.core.exception.CouponNotFoundException;
import me.khw7385.waitingroom.coupon.core.exception.CouponOutOfStockException;
import me.khw7385.waitingroom.coupon.domain.Coupon;
import me.khw7385.waitingroom.coupon.domain.CouponIssue;
import me.khw7385.waitingroom.coupon.domain.dto.CouponAvailableInfo;
import me.khw7385.waitingroom.coupon.domain.repository.CouponIssueRepository;
import me.khw7385.waitingroom.coupon.domain.repository.CouponRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class CouponServiceUnitTest {
    @Mock
    private CouponRepository doubleCouponRepository;
    @Mock
    private CouponIssueRepository doubleCouponIssueRepository;
    @InjectMocks
    private CouponService couponService;

    @Nested
    @DisplayName("쿠폰 발급 테스트")
    class CouponIssueTest {
        @Mock
        private CouponIssueCommand doubleCouponIssueCommand;
        @Mock
        private Coupon doubleCoupon;
        @Spy
        private Coupon spyCoupon = Coupon.builder().id(1L).name("spy").quantity(1).build();
        @Spy
        private Coupon spyCouponQuantity0 = Coupon.builder().id(1L).name("spy").quantity(0).build();

        @Mock
        private CouponIssue doubleCouponIssue;

        @Test
        @DisplayName("쿠폰 발급 성공")
        void issueCoupon_success(){
            // given
            given(doubleCouponRepository.findCouponForIssue(anyLong())).willReturn(Optional.of(spyCoupon));
            given(doubleCouponIssueRepository.existsCouponIssue(anyLong())).willReturn(false);
            given(doubleCouponIssueRepository.save(any(CouponIssue.class))).willReturn(doubleCouponIssue);

            // when
            couponService.issueCoupon(doubleCouponIssueCommand);

            // then
            assertThat(spyCoupon.getQuantity()).isEqualTo(0);
        }

        @Test
        @DisplayName("쿠폰 발급 에러 - 쿠폰 존재하지 않음")
        void issueCoupon_throws_CouponNotFoundException(){
            // given
            given(doubleCouponRepository.findCouponForIssue(anyLong())).willReturn(Optional.empty());

            // when && then
            assertThatThrownBy(() -> couponService.issueCoupon(doubleCouponIssueCommand))
                    .isInstanceOf(CouponNotFoundException.class);
        }

        @Test
        @DisplayName("쿠폰 발급 에러 - 해당 쿠폰을 이미 발급 받음")
        void issueCoupon_throws_CouponAlreadyIssuedException(){
            // given
            given(doubleCouponRepository.findCouponForIssue(anyLong())).willReturn(Optional.of(doubleCoupon));
            given(doubleCouponIssueRepository.existsCouponIssue(anyLong())).willReturn(true);

            // when & then
            assertThatThrownBy(() -> couponService.issueCoupon(doubleCouponIssueCommand))
                    .isInstanceOf(CouponAlreadyIssuedException.class);
        }

        @Test
        @DisplayName("쿠폰 발급 에러 - 해당 쿠폰 모두 소진")
        void issueCoupon_throws_CouponOutOfStockException(){
            // given
            given(doubleCouponRepository.findCouponForIssue(anyLong())).willReturn(Optional.of(spyCouponQuantity0));
            given(doubleCouponIssueRepository.existsCouponIssue(anyLong())).willReturn(false);

            // when & then
            assertThatThrownBy(() -> couponService.issueCoupon(doubleCouponIssueCommand))
                    .isInstanceOf(CouponOutOfStockException.class);
        }
    }

    @Nested
    @DisplayName("사용자가 가진 유효한 쿠폰 조회")
    class CouponFetchTest{
        @Spy
        List<CouponAvailableInfo> infos = List.of(
                new CouponAvailableInfo(1L, 1L, "example1", LocalDateTime.now()),
                new CouponAvailableInfo(2L, 2L, "example2", LocalDateTime.now()
                ));

        @Test
        @DisplayName("유효한 쿠폰 조회 - 성공")
        void findCoupons_success(){
            // given
            given(doubleCouponRepository.findAvailableCoupons(anyLong(), any())).willReturn(infos);

            // when
            CouponListAvailableResult result = couponService.findCoupons(1L);

            // then
            assertThat(result.coupons().size()).isEqualTo(infos.size());
        }
    }
}
