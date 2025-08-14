package me.khw7385.waitingroom.common.web.dto;

public record ErrorResponse(
        String errorCode,
        String message
) {
}
