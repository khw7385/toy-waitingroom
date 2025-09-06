package me.khw7385.waitingroom.common.web.exception;

import static me.khw7385.waitingroom.common.web.exception.ErrorCode.*;

public class ServiceUnavailableException extends RequestException{
    public ServiceUnavailableException(String serviceName) {
        super(SERVICE_UNAVAILABLE.getMeesage().formatted(serviceName));
    }

    @Override
    public String getErrorCode() {
        return SERVICE_UNAVAILABLE.name();
    }
}
