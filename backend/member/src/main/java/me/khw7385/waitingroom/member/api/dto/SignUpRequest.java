package me.khw7385.waitingroom.member.api.dto;

import me.khw7385.waitingroom.member.application.dto.SignUpCommand;

public record SignUpRequest(
        String loginId,
        String password
) {

    public SignUpCommand toCommand(){
        return new SignUpCommand(loginId, password);
    }
}
