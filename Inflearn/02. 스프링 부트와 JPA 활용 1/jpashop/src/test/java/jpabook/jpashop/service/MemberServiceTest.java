package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
//    @Rollback(false) // 원래 테스트에 Transactional 이 붙어있으면 persist를 flush를 하지 않음 그래서 롤백을 false하거나 em.flush() 해줘야함.
    public void 회원가입() throws Exception{
        // given
        Member member = new Member();
        member.setName("Kim");

        //when
        Long savedId = memberService.join(member);

        //then
//        em.flush(); // @Rollback(false)를 하던가 em.flush(); 하던가!
        assertEquals(member, memberRepository.findOne(savedId));

    }

    @Test
    public void 중복_회원_예외() throws Exception{
        // given
        Member member1 = new Member();
        member1.setName(("kim"));

        Member member2 = new Member();
        member2.setName(("kim"));


        // when
        memberService.join(member1);

        // 아래처럼 assertThrows로 할 수 있음 (JUnit5에서) 밑에 Assertions.fail도 지워도 됨
        // JUnit 4는 @Test(expected = IllegalStateException.class)하면 됨
//        try { // 트라이 캐치로 정상 return 할 수도 있고
            assertThrows(IllegalStateException.class, ()-> {memberService.join(member2);}); // 예외가 발생해야 한다!
//        }catch (IllegalStateException e){
//            return;
//        }

        // then
//        fail("예외가 발생해야 한다.");

    }

}
