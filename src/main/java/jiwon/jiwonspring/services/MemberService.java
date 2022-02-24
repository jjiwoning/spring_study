package jiwon.jiwonspring.services;

import jiwon.jiwonspring.domain.Member;
import jiwon.jiwonspring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


// 테스트 클래스 생성 단축키 command shift t
// 이거를 넣어주면 스프링 컨테이너에 해당 클래스가 등록이 된다.
//@Service
@Transactional
public class MemberService {
    // 회원 서비스에 필요한 것 -> 회원 리포지토리
    // 서비스 클래스의 메서드 이름은 비즈니스에 가까운 이름으로 지어줘야 한다. -> 비즈니스에 의존적으로 설계해야 된다.

    private final MemberRepository memberRepository;

    // MemberService의 생성자
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member){
        // 메서드 동작 시간 메서드
        long start = System.currentTimeMillis();

        // 동명이인 가입 불가 로직 추가 (중복회원 x)  (단축키 : 커맨드 옵션 v)
        try {
            validateDuplicateMember(member); // (컨트롤 + t 로 메서드로 뽑을 수 있다)
            memberRepository.save(member);
            return member.getId();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // null이 아닌 어떤 값이 존재한다면 (조건)
                    throw new IllegalStateException("이미 존재하는 회원 입니다."); //해당 코드가 동작을 한다.
                    // Optional이라서 가능한 코드
                    // null이 발생할 수 있으면 Optional로 감싸서 반환해주면 좋다. 해당 코드를 적을 수 있기 때문에
        });
    }
    // 전체 회원 조회
    public List<Member> findMembers(){
        long start = System.currentTimeMillis();

        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }
    }

    public Optional<Member> findOne(Long memberId){
            return memberRepository.findById(memberId);
    }
}
