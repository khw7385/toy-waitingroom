package me.khw7385.waitingroom.member.application.dto;

public record LoginCommand(
        String loginId,
        String password
) {
}
