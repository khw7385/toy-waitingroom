package me.khw7385.waitingroom.common.web.exception;

public abstract class InternalServerException extends RequestException {
    public InternalServerException(String message) {
        super(message);
    }
}
