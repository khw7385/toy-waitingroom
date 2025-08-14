package me.khw7385.waitingroom.member.application.dto;

import me.khw7385.waitingroom.member.domain.dto.MemberSpec;

public record SignUpCommand(
        String loginId,
        String password
) {
    public MemberSpec toSpec(String encodedPassword){
        return new MemberSpec(loginId, encodedPassword);
    }
}
