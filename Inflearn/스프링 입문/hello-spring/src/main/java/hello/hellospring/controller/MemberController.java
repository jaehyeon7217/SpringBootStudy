package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    // new로 사용하면 하나의 인스턴스를 생성하는것이 아님
//    private final MemberService memberService = new MemberService();

    private final MemberService memberService;

    // Autowired하려면 service에 빈등록 (@Service)를 해줘야함. -> Dependency Injection
    // @Component 어노테이션이 있으면 자동으로 Spring bean에 등록됨 근데 Controller, Service, Repository 모두 Component 를 가지고 있음.
    // @ComponentScan이라는 어노테이션에 의해서 bean 등록되고 찾아서 Autowired 해주는거임.
    // ComponentScan은 HelloSpringApplication의 SpringBootApplication에 있음.
    // 그래서 패키지의 2스텝 하위에서만 스캔이 일어남.
    // 생성자 주입 (권장)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 필드 주입
    // 별로 안좋음
//    @Autowired private MemberService memberService;

    // setter 주입
    // setter가 public 이라서 바뀔 수 있는 위험이 생김
//    private MemberService memberService;
//
//    @Autowired
//    public void setMemberService(MemberService memberService) {
//        this.memberService = memberService;
//    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
