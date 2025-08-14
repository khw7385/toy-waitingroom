package me.khw7385.waitingroom.member.core.exception;

import me.khw7385.waitingroom.common.web.exception.BadRequestException;

import static me.khw7385.waitingroom.common.web.exception.ErrorCode.*;


public class DuplicateLoginIdException extends BadRequestException {
    public DuplicateLoginIdException() {
        super(DUPLICATE_LOGIN_ID.getMeesage());
    }

    @Override
    public String getErrorCode() {
        return DUPLICATE_LOGIN_ID.name();
    }
}
