package me.khw7385.waitingroom.member.application;

import jakarta.annotation.PostConstruct;
import me.khw7385.waitingroom.common.jwt.TokenManager;
import me.khw7385.waitingroom.common.jwt.core.JwtProperties;
import me.khw7385.waitingroom.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenFacade {
    private static final long HOURS_TO_MILLS = 60 * 60 * 1000;

    private final TokenManager tokenManager;
    private final JwtProperties jwtProperties;

    private long accessTokenTime;

    @PostConstruct
    private void init(){
        accessTokenTime = jwtProperties.accessTokenExpireTimeInHours() * HOURS_TO_MILLS;
    }

    public String issueToken(Member member){
        return tokenManager.createToken(member.getId(), accessTokenTime);
    }
}
