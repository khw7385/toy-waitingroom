package me.khw7385.waitingroom.member.api;

import me.khw7385.waitingroom.member.api.dto.LoginRequest;
import me.khw7385.waitingroom.member.api.dto.SignUpRequest;
import me.khw7385.waitingroom.member.api.swagger.AuthSwagger;
import me.khw7385.waitingroom.member.application.AuthFacade;
import lombok.RequiredArgsConstructor;
import me.khw7385.waitingroom.common.web.dto.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthSwagger {
    private final AuthFacade authFacade;

    @PostMapping("/sign-up")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody SignUpRequest request){
        authFacade.signUp(request.toCommand());
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public SuccessResponse<?> login(@RequestBody LoginRequest request) {
        String token = authFacade.login(request.toCommand());
        return new SuccessResponse<>(token);
    }

    @PostMapping("/test-token")
    @ResponseStatus(HttpStatus.CREATED)
    public SuccessResponse<?> createTestToken(){
        String token = authFacade.issueTestToken();
        return new SuccessResponse<>(token);
    }
}
