package me.khw7385.waitingroom.member.infra.client;

import lombok.RequiredArgsConstructor;
import me.khw7385.waitingroom.common.web.dto.SuccessResponse;
import me.khw7385.waitingroom.member.application.dto.CouponListAvailablePortResponse;
import me.khw7385.waitingroom.member.application.port.outbound.CouponClientPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CouponRestClient implements CouponClientPort {
    private final RestClient couponBaseRestClient;

    private static final String MEMBER_COUPONS_FIND_PATH_FORMAT = "/internal/members/%d/coupons";

    @Override
    public CouponListAvailablePortResponse findAvailableCoupons(Long memberId) {
        SuccessResponse<CouponListAvailablePortResponse> successResponse = couponBaseRestClient.get()
                .uri(String.format(MEMBER_COUPONS_FIND_PATH_FORMAT, memberId))
                .retrieve()
                .body(new ParameterizedTypeReference<SuccessResponse<CouponListAvailablePortResponse>>() {
                });

        return Optional.ofNullable(successResponse)
                .map(SuccessResponse::data)
                .orElse(CouponListAvailablePortResponse.empty());
    }
}
