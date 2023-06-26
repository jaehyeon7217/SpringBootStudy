package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // @Service나 @Repository를 쓰지 않고 자바 코드만을 이용한 객체 주입
    // @Controller는 안됨

    // 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.
    // -> DB를 바꾸거나 하는 등 인터페이스의 구현체를 바꿀 때 사용
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }

    // 메모리에서 DB로 바꿀 때 요거만 바꾸면 자동으로 주입할 수 있음.
//    @Bean
//    public MemberRepository memberRepository(){
//        return new DBMemberRepository();
//    }
}
