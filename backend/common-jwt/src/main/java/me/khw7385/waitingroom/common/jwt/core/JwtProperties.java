package me.khw7385.waitingroom.common.jwt.core;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
        String secret,
        String issuer,
        Long accessTokenExpireTimeInHours
) {
}
