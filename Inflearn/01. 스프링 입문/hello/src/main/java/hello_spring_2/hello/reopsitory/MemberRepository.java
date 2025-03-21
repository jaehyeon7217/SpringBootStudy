package hello_spring_2.hello.reopsitory;

import hello_spring_2.hello.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // Optional 은 null 을 반환하기 위해서
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
