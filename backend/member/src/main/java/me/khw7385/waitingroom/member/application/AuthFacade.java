package me.khw7385.waitingroom.member.application;

import me.khw7385.waitingroom.member.application.dto.LoginCommand;
import me.khw7385.waitingroom.member.application.dto.SignUpCommand;
import me.khw7385.waitingroom.member.core.exception.DuplicateLoginIdException;
import me.khw7385.waitingroom.member.core.exception.NotFoundLoginIdException;
import me.khw7385.waitingroom.member.core.exception.NotMatchLoginIdAndPasswordException;
import me.khw7385.waitingroom.member.domain.Member;
import me.khw7385.waitingroom.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AuthFacade {
    private final TokenFacade tokenFacade;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(SignUpCommand command){
        boolean existsMember = memberRepository.existsByLoginId(command.loginId());

        if(existsMember){
            throw new DuplicateLoginIdException();
        }

        String encodedPassword = passwordEncoder.encode(command.password());
        memberRepository.save(Member.createMember(command.toSpec(encodedPassword)));
    }

    @Transactional(readOnly = true)
    public String login(LoginCommand command){
        Member member = memberRepository.findByLoginId(command.loginId()).orElseThrow(NotFoundLoginIdException::new);

        if (!passwordEncoder.matches(command.password(), member.getPassword())) {
            throw new NotMatchLoginIdAndPasswordException();
        }

        return tokenFacade.issueToken(member);
    }

    @Transactional(readOnly = true)
    public String issueTestToken(){
        Member member = memberRepository.findById(1L).orElseThrow(NotFoundLoginIdException::new);
        return tokenFacade.issueToken(member);
    }
}
