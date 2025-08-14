package me.khw7385.waitingroom.common.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import me.khw7385.waitingroom.common.jwt.core.JwtProperties;
import me.khw7385.waitingroom.common.jwt.core.exception.TokenExpiredException;
import me.khw7385.waitingroom.common.jwt.core.exception.TokenInvalidException;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

public class TokenManager {
    private String issuer;
    private SecretKey secretKey;

    public TokenManager(JwtProperties jwtProperties) {
        // 토큰 발행자
        issuer = jwtProperties.issuer();

        // 시크릿 키
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.secret());
        secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createToken(Long memberId, long expirationTime){
        String jti = UUID.randomUUID().toString().substring(0, 16) + memberId;
        Date now = new Date();

        return Jwts.builder()
                .id(jti)
                .issuer(issuer)
                .claim("memberId", memberId)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expirationTime))
                .signWith(secretKey)
                .compact();
    }

    public void parseClaims(String token) {
        try {
            Jwts.parser()
                    .requireIssuer(issuer)
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }catch (ExpiredJwtException ex){
            throw new TokenExpiredException();
        }catch (JwtException x){
            throw new TokenInvalidException();
        }
    }
}
