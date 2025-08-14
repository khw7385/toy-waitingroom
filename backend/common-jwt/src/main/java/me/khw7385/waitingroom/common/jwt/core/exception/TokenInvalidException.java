package me.khw7385.waitingroom.common.jwt.core.exception;

import me.khw7385.waitingroom.common.web.exception.UnauthorizedException;

import static me.khw7385.waitingroom.common.web.exception.ErrorCode.*;

public class TokenInvalidException extends UnauthorizedException {
    public TokenInvalidException() {
        super(TOKEN_INVALID.getMeesage());
    }

    @Override
    public String getErrorCode() {
        return TOKEN_INVALID.name();
    }
}
