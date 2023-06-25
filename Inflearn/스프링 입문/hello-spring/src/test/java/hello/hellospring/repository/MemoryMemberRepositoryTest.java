package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();


    // 테스트는 순서의 의존성이 없어야 한다.
    // 그래서 각 테스트가 끝나면 메모리를 비워주는 작업을 해주는 용도
    // 이거 안하면 findALl하고 메모리가 안지워져서 오류남
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        System.out.println("result = " + (result == member));

        // org.jnuit.jupiter.api
//        Assertions.assertEquals(member,result);
//        Assertions.assertEquals(member,null);

        // 원래는 Assertions.AssertThat인데 스태틱으로
        // import static org.assertj.core.api.Assertions.*;
        // 이거 하니까 바로 assertThat()으로 가능하게 됨
        assertThat(member).isEqualTo(result);
//        assertThat(member).isEqualTo(null);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1  = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2  = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
