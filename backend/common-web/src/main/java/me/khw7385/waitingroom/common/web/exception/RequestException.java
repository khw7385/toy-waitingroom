package me.khw7385.waitingroom.common.web.exception;

public abstract class RequestException extends RuntimeException {
    public RequestException(String message) {
        super(message);
    }
    public abstract String getErrorCode();
}
