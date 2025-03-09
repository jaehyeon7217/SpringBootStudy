package hello_spring_2.hello.service;

import hello_spring_2.hello.domain.Member;
import hello_spring_2.hello.reopsitory.MemberRepository;
import hello_spring_2.hello.reopsitory.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {

    // DI (Dependency Injection)
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     * 회원가입
     *
     */
    public Long join(Member member) {
        // 같은 이름의 중복 회원은 가입 불가
        validateDuplicateMember(member);


        memberRepository.save(member);
        return member.getId();
    }

    /*
    * 전체 회원 조회
     */

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent( m ->{
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

}
