package me.khw7385.waitingroom.gateway.filter;

import lombok.RequiredArgsConstructor;
import me.khw7385.waitingroom.common.jwt.TokenManager;
import me.khw7385.waitingroom.gateway.exception.TokenRequiredException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements HandlerFilterFunction<ServerResponse, ServerResponse> {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    private final TokenManager tokenManager;

    @Override
    public ServerResponse filter(ServerRequest request, HandlerFunction<ServerResponse> next) throws Exception {
        Optional<String> tokenOptional = extractToken(request);

        if(tokenOptional.isEmpty()){
            throw new TokenRequiredException();
        }
        tokenManager.parseClaims(tokenOptional.get());

        return next.handle(request);
    }

    private Optional<String> extractToken(ServerRequest serverRequest){
        String authorizationValue = serverRequest.headers().firstHeader(AUTHORIZATION_HEADER);
        if (authorizationValue == null || !authorizationValue.startsWith(TOKEN_PREFIX)) return Optional.empty();
        return Optional.of(authorizationValue.substring(TOKEN_PREFIX.length()));
    }
}
