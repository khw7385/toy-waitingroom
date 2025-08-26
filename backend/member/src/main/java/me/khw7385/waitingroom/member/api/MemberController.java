package me.khw7385.waitingroom.member.api;

import lombok.RequiredArgsConstructor;
import me.khw7385.waitingroom.common.web.dto.SuccessResponse;
import me.khw7385.waitingroom.member.api.dto.MemberCouponListResponse;
import me.khw7385.waitingroom.member.application.MemberService;
import me.khw7385.waitingroom.member.application.dto.MemberCouponListResult;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/members/coupons")
    public SuccessResponse<?> findMemberCoupons(@AuthenticationPrincipal Long memberId){
        MemberCouponListResult result = memberService.findMemberCoupons(memberId);

        return new SuccessResponse<>(MemberCouponListResponse.from(result));
    }
}
