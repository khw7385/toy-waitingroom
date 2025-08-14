package me.khw7385.waitingroom.common.web.exception;

public abstract class NotFoundException extends RequestException {
    public NotFoundException(String message) {
        super(message);
    }
}
