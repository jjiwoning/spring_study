package services;

import jiwon.jiwonspring.domain.Member;

import jiwon.jiwonspring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


class MemberServiceTest {

    // 테스트 코드는 한글로 적어도 된다. 직관적으로 쉽게 볼 수 있게

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        // 중요!! 생성자의 개념을 잘 알아야 이 코드 이해 가능
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
        // -> 두 객체가 가리키는 포인터가 같아진다. -> DI 라고 한다.
    }

    @AfterEach // 각 메서드가 끝날때 마다 어떤 동작을 수행하게 하는 코드
    public void afterEach(){
        // 각 메서드가 끝날때마다 데이터를 지워주게 한다.
        // 이 메서드는 상당히 중요한 메서드 *** 잘 알아두기
        // 클래스 내부 변수가 static 으로 선언되어 있어서 다른 객체여도 하나의 변수를 공유하기 때문에 가능한 코드.
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given : 이런 상황이 주어졌을 때 -> 이 데이터를 기반으로 하는구나!
        Member member = new Member();
        member.setName("tomtom");

        // when : 이걸 실행했을 때 -> 이걸 검증하는구나!
        Long saveId = memberService.join(member);

        // then : 이 결과가 나와야 해 -> 검증부구나!
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    // 중복 회원이 발생했을 때 코드가 정상적으로 작동 하는지에 대해 테스트
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");
//        try{
//            memberService.join(member2);
//            fail();
//        }catch(IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원 입니다.");
//        }

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}