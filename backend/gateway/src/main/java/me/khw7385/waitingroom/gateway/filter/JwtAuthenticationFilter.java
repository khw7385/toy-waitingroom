package me.khw7385.waitingroom.gateway.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import me.khw7385.waitingroom.common.jwt.TokenManager;
import me.khw7385.waitingroom.common.jwt.dto.TokenMemberClaim;
import me.khw7385.waitingroom.gateway.exception.TokenRequiredException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.*;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Value("${server.servlet.context-path}")
    private String SERVLET_CONTEXT_PATH;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String MEMBER_ID_HEADER = "X-MEMBER-ID";
    private static final List<String> PERMITTED_URL_PATTERNS = List.of(
            "/auth/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/*-service/api-docs",
            "/api-docs/**",
            "/coupons"
    );

    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private final TokenManager tokenManager;
    private final HandlerExceptionResolver exceptionResolver;

    public JwtAuthenticationFilter(TokenManager tokenManager, @Qualifier(value = "handlerExceptionResolver") HandlerExceptionResolver exceptionResolver) {
        this.tokenManager = tokenManager;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return PERMITTED_URL_PATTERNS.stream()
                .anyMatch(pattern -> pathMatcher.match(pattern, request.getRequestURI()));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> tokenOptional = extractToken(request);

        if(tokenOptional.isEmpty()){
            try{
                throw new TokenRequiredException();
            }catch(TokenRequiredException e){
                exceptionResolver.resolveException(request, response, null, e);
                return;
            }
        }

        TokenMemberClaim memberClaim = tokenManager.parseClaims(tokenOptional.get());
        MutableHttpServletRequestWrapper requestWrapper = new MutableHttpServletRequestWrapper(request);
        requestWrapper.setHeader(MEMBER_ID_HEADER, String.valueOf(memberClaim.memberId()));

        filterChain.doFilter(requestWrapper, response);
    }

    private Optional<String> extractToken(HttpServletRequest request){
        String authorizationValue = request.getHeader(AUTHORIZATION_HEADER);
        if(authorizationValue == null || !authorizationValue.startsWith(TOKEN_PREFIX)) return Optional.empty();
        return Optional.of(authorizationValue.substring(TOKEN_PREFIX.length()));
    }

    private static class MutableHttpServletRequestWrapper extends HttpServletRequestWrapper {
        private HashMap<String, String> customHeaders;

        public MutableHttpServletRequestWrapper(HttpServletRequest request){
            super(request);
            this.customHeaders = new HashMap<>();
        }

        public void setHeader(String headerName, String headerValue){
            customHeaders.put(headerName, headerValue);
        }

        @Override
        public String getHeader(String name) {
            if(customHeaders.containsKey(name)){
                return customHeaders.get(name);
            }
            return super.getHeader(name);
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            Set<String> set = new HashSet<>(customHeaders.keySet());
            Enumeration<String> e = super.getHeaderNames();
            while (e.hasMoreElements()) {
                set.add(e.nextElement());
            }
            return Collections.enumeration(set);
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            List<String> values = new ArrayList<>();

            if(customHeaders.containsKey(name)){
                values.add(customHeaders.get(name));
            }

            Enumeration<String> e = super.getHeaders(name);
            while(e.hasMoreElements()){
                values.add(e.nextElement());
            }

            return Collections.enumeration(values);
        }
    }
}
