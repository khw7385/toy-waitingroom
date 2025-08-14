package me.khw7385.waitingroom.common.jwt.core.exception;

import me.khw7385.waitingroom.common.web.exception.UnauthorizedException;

import static me.khw7385.waitingroom.common.web.exception.ErrorCode.*;


public class TokenExpiredException extends UnauthorizedException {
    public TokenExpiredException() {
        super(TOKEN_EXPIRED.getMeesage());
    }

    @Override
    public String getErrorCode() {
        return TOKEN_EXPIRED.name();
    }
}
