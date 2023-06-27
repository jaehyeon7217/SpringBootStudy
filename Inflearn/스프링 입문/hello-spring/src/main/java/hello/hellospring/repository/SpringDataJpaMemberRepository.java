package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // 인터페이스간 상속
    // JpaRepository 를 상속하면 자동으로 구현체를 만들고 Bean에 등록한다.

    @Override
    Optional<Member> findByName(String name);
}
