package me.khw7385.waitingroom.member.api.swagger;

import me.khw7385.waitingroom.member.api.dto.SignUpRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface AuthSwagger {

    void signUp(@RequestBody SignUpRequest request);
}
