package me.khw7385.waitingroom.coupon.integration.application;

import me.khw7385.waitingroom.coupon.application.CouponService;
import me.khw7385.waitingroom.coupon.application.dto.CouponIssueCommand;
import me.khw7385.waitingroom.coupon.domain.Coupon;
import me.khw7385.waitingroom.coupon.domain.CouponIssue;
import me.khw7385.waitingroom.coupon.domain.repository.CouponIssueRepository;
import me.khw7385.waitingroom.coupon.domain.repository.CouponRepository;
import me.khw7385.waitingroom.coupon.infra.persistence.JPACouponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@SpringBootTest
public class CouponServiceIntegrationTest {
    @Autowired
    private CouponService couponFacade;
    @MockitoBean
    private CouponIssueRepository couponIssueRepository;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private JPACouponRepository jpaCouponRepository;

    @Nested
    @DisplayName("쿠폰 발급 - 동시성 테스트")
    class CouponIssueConcurrencyTest{
        private CouponIssueCommand couponIssueCommand;
        @Mock
        private CouponIssue doubleCouponIssue;

        @BeforeEach
        void beforeEach(){
            couponIssueCommand = new CouponIssueCommand(1L, 1L);
            Coupon coupon = Coupon.builder()
                    .name("test")
                    .quantity(1000)
                    .build();
            couponRepository.save(coupon);
        }

        @Test
        void issueCoupon_100_request_success() throws InterruptedException {
            // given
            int request = 1000;
            ExecutorService executorService = Executors.newFixedThreadPool(32);
            CountDownLatch countDownLatch = new CountDownLatch(request);

            given(couponIssueRepository.existsCouponIssue(any(Long.class))).willReturn(false);
            given(couponIssueRepository.save(any(CouponIssue.class))).willReturn(doubleCouponIssue);

            // when
            IntStream.range(0, request).forEach((i) -> {
                executorService.execute(() -> {
                    try{
                        couponFacade.issueCoupon(couponIssueCommand);
                    }finally{
                        countDownLatch.countDown();
                    }
                });
            });
            countDownLatch.await();
            executorService.shutdown();
            Coupon coupon = jpaCouponRepository.findById(1L).orElseThrow();

            // then
            assertThat(coupon.getQuantity()).isEqualTo(0);
        }
    }
}
