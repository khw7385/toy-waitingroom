package me.khw7385.waitingroom.member.domain;

import jakarta.persistence.*;
import me.khw7385.waitingromm.common.data.domain.BaseTimeEntity;
import me.khw7385.waitingroom.member.domain.dto.MemberSpec;
import lombok.*;

@Getter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String loginId;
    private String password;

    public static Member createMember(MemberSpec spec){
        return Member.builder()
                .loginId(spec.loginId())
                .password(spec.password())
                .build();
    }
}
