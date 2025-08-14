package me.khw7385.waitingroom.common.web.exception;

public abstract class BadRequestException extends RequestException {
    public BadRequestException(String message) {
        super(message);
    }
}
