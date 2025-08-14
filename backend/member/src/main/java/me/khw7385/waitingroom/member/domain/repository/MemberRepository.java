package me.khw7385.waitingroom.member.domain.repository;

import me.khw7385.waitingroom.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByLoginId(String loginId);
    Optional<Member> findByLoginId(String loginId);
}
