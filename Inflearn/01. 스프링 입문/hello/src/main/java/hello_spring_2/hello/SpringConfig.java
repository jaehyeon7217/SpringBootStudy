package hello_spring_2.hello;

import hello_spring_2.hello.reopsitory.MemberRepository;
import hello_spring_2.hello.reopsitory.MemoryMemberRepository;
import hello_spring_2.hello.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
