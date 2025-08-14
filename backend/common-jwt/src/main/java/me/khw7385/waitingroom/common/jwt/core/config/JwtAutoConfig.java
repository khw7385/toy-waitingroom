package me.khw7385.waitingroom.common.jwt.core.config;

import me.khw7385.waitingroom.common.jwt.TokenManager;
import me.khw7385.waitingroom.common.jwt.core.JwtProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public TokenManager tokenManager(JwtProperties jwtProperties) {
        return new TokenManager(jwtProperties);
    }
}
