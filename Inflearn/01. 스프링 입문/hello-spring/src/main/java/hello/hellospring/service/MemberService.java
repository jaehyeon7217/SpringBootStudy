package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;

// 원래는 @Component 로 등록해야하는데 Service 에 포함되어 있음.
//@Service
@Transactional
public class MemberService {
    // 다른 인스턴스를 참조
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // 싱글톤으로 디자인
    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // 회원가입
    public Long join(Member member) {
        // 시간 측정을 위한 로직
        long start = System.currentTimeMillis();

        // 중복 회원 가입 막기
        // 이거도 alt + enter 누르면 앞에 반환값을 자동으로 만들어줌
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        // ctrl + shift + alt + t 누르면 리팩토링 툴 나오는데 여기서 extract method 누르면 클래스 분리 가능
        try {
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        }finally{ // 에러가 터져도 무조건 호출
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
