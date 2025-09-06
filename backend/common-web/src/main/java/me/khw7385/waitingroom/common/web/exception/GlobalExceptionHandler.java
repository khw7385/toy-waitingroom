package me.khw7385.waitingroom.common.web.exception;

import me.khw7385.waitingroom.common.web.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(BadRequestException ex){
        return new ErrorResponse(ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse handle(UnauthorizedException ex){
        return new ErrorResponse(ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handle(NotFoundException ex){
        return new ErrorResponse(ex.getErrorCode(), ex.getMessage());
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(InternalServerException ex){
        return new ErrorResponse(ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorResponse handle(ServiceUnavailableException ex){
        return new ErrorResponse(ex.getErrorCode(), ex.getMessage());
    }
}
