package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MemberTestRepositoryTest {

    @Autowired
    MemberSimpleTestRepository memberSimpleTestRepository;

    @Test
    @Transactional // springFramwork 에서 제공하는걸 사용해야함.
                   // Entity 는 항상 Transaction 에서 실행되어야함 아니면 에러남
                   // Test에서 Transactional 이 있으면 바로 Rollback 시켜버림.
                   // 확인하고 싶으면 @Rollback(false) 이러면 실제 데이터가 쌓임
    @Rollback(false)
    public void testMember() throws Exception{
        //given
        MemberSimpleTest memberTest = new MemberSimpleTest();
        memberTest.setUserName("memberA");
        //when
        Long savedId = memberSimpleTestRepository.save(memberTest);
        MemberSimpleTest findMemberTest = memberSimpleTestRepository.find(savedId);

        //then
        Assertions.assertThat(findMemberTest.getId()).isEqualTo(memberTest.getId());
        Assertions.assertThat(findMemberTest.getUserName()).isEqualTo(memberTest.getUserName());
        Assertions.assertThat(findMemberTest).isEqualTo(memberTest);
        // 같은 트랜잭션이면 영속성 컨텍스트안에서 Id가 같으면 같은 Entity로 인식하고 1차 캐시에서 꺼내와서 기존것이 나가버림.
        System.out.println("findMember == member : " + (findMemberTest == memberTest));

    }

}
