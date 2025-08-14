package me.khw7385.waitingroom.member.api.dto;

import me.khw7385.waitingroom.member.application.dto.LoginCommand;

public record LoginRequest(
        String loginId,
        String password
) {
    public LoginCommand toCommand(){
        return new LoginCommand(loginId, password);
    }
}
