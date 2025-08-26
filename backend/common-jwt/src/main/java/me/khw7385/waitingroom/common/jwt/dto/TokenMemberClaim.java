package me.khw7385.waitingroom.common.jwt.dto;

import io.jsonwebtoken.Claims;

public record TokenMemberClaim(
        Long memberId
) {
    public static TokenMemberClaim from(Claims claims) {
        return new TokenMemberClaim(
                claims.get("memberId", Long.class)
        );
    }
}
