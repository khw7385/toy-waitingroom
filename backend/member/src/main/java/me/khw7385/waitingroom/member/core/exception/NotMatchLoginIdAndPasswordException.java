package me.khw7385.waitingroom.member.core.exception;

import me.khw7385.waitingroom.common.web.exception.BadRequestException;

import static me.khw7385.waitingroom.common.web.exception.ErrorCode.*;

public class NotMatchLoginIdAndPasswordException extends BadRequestException {
    public NotMatchLoginIdAndPasswordException() {
        super(NOT_MATCH_LOGIN_ID_AND_PASSWORD.getMeesage());
    }

    @Override
    public String getErrorCode() {
        return NOT_MATCH_LOGIN_ID_AND_PASSWORD.name();
    }
}
