package me.khw7385.waitingroom.common.web.exception;

public abstract class UnauthorizedException extends RequestException{
    public UnauthorizedException(String message) {
        super(message);
    }
}
