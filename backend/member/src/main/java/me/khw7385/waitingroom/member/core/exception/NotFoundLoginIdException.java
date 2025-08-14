package me.khw7385.waitingroom.member.core.exception;

import me.khw7385.waitingroom.common.web.exception.NotFoundException;

import static me.khw7385.waitingroom.common.web.exception.ErrorCode.NOT_FOUND_LOGIN_ID;

public class NotFoundLoginIdException extends NotFoundException {
    public NotFoundLoginIdException() {
        super(NOT_FOUND_LOGIN_ID.getMeesage());
    }

    @Override
    public String getErrorCode() {
        return NOT_FOUND_LOGIN_ID.name();
    }
}
