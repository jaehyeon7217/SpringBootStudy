package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    // JDBC 쓸 때
//    private DataSource dataSource;

//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    // JPA 쓸 때
//    private EntityManager em;
//
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // Spring Data JPA
    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // @Service나 @Repository를 쓰지 않고 자바 코드만을 이용한 객체 주입
    // @Controller는 안됨

    // 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.
    // -> DB를 바꾸거나 하는 등 인터페이스의 구현체를 바꿀 때 사용
    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository());
//    }
    public MemberService memberService() {
        // Spring data jpa는 memberRepository를 인터페이스로 구현해서 클래스가 필요없음
        return new MemberService(memberRepository);
    }


    // 메모리에서 DB로 바꿀 때 요거만 바꾸면 자동으로 주입할 수 있음. 
//    @Bean
//    public MemberRepository memberRepository(){
        // 메모리에 저장하는 방식
//        return new MemoryMemberRepository();

        // 순수 JDBC
//        return new JdbcMemberRepository(dataSource);
        // 구현체만 바꾸면 바꿀 수 있다. (갈아끼우는 것 처럼)
        // 개방-폐쇄 원칙(OCP, Open-Closed Principle)
        // 확장에는 열려있고, 수정 및 변경에는 닫혀있다.
        // 기존 코드는 전혀 손대지 않고, 설정만으로 구현 클래스를 변경 할 수 있다.

        // JDBC Template
//        return new JdbcTemplateMemberRepository(dataSource);

        // JPA
//        return new JpaMemberRepository(em);
//    }



    // AOP 빈 등록
//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }




}
