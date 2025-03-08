package hello_spring_2.hello.repository;

import hello_spring_2.hello.domain.Member;
import hello_spring_2.hello.reopsitory.MemberRepository;
import hello_spring_2.hello.reopsitory.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*; // alt + enter 누르면 가능

public class MemoryMemberRepositoryTest {
    MemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){ // 하나의 테스트가 끝날 때 마다 비워주는 역할
        repository.clearStore();
    }



    @Test
    public void save(){
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        System.out.println("result = " + (result == member));

        // org.junit.jupiter.api.Assertions;
//         Assertions.assertEquals(member, result);
//         Assertions.assertEquals(member, null);


        // org.assertj.core.api.Assertions
        assertThat(member).isEqualTo(result);

//        assertThat(member).isEqualTo(null);


    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring1").get();
//        Member result = repository.findByName("Spring2").get();

        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }


}
