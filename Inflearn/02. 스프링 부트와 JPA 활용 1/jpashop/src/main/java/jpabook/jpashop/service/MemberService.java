package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor // 5. 이게 제일 좋은 방법 (AllArgsConstructor도 좋지만 Required가 더 좋음)
// @Transactional(readOnly = true) 이거 달아놓으면 기본적으로 리드온리고 join 처럼 @Transactional 달려 있으면 메서드에 달린게 우선 실행
public class MemberService {

//    @Autowired // 1. 요즘은 아래같은 생성자 주입이 좋음
    private final MemberRepository memberRepository;

    // 2. 생성자 주입
//    @Autowired // 3. 생략가능
//    public MemberService(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }
    // 4. 이걸 @RequiredArgsConstructor 로 대체함

    // 회원 가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        // EXCEPTION
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    // readOnly = true 하면 성능이 좋아짐
    // JPA 플러쉬, 더티체크 안하고 읽기 전용이라 리소스를 적게 쓰라고 알려줌
    @Transactional(readOnly = true)
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findOne(Long  memberId){
        return memberRepository.findOne(memberId);
    }

}
