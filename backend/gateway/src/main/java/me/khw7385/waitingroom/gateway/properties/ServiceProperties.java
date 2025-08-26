package me.khw7385.waitingroom.gateway.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "service.host")
public record ServiceProperties(
        String member,
        String coupon
) {
}
