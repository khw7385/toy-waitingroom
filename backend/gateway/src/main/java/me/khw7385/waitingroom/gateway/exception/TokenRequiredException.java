package me.khw7385.waitingroom.gateway.exception;

import me.khw7385.waitingroom.common.web.exception.UnauthorizedException;

import static me.khw7385.waitingroom.common.web.exception.ErrorCode.*;

public class TokenRequiredException extends UnauthorizedException {
    public TokenRequiredException() {
        super(TOKEN_REQUIRED.getMeesage());
    }

    @Override
    public String getErrorCode() {
        return TOKEN_REQUIRED.name();
    }
}
