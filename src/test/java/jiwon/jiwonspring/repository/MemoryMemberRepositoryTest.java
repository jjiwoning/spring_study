package jiwon.jiwonspring.repository;

import jiwon.jiwonspring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    // 객체 생성
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 각 메서드가 끝날때 마다 어떤 동작을 수행하게 하는 코드
    public void afterEach(){
        // 각 메서드가 끝날때마다 데이터를 지워주게 한다.
        // 이 메서드는 상당히 중요한 메서드 *** 잘 알아두기
        repository.clearStore();
    }

    @Test // 이거를 해주면 아래의 코드를 실행해줄 수 있다.
    public void save(){
        // 메인 메서드 코드를 쓰듯이 쓰면 된다.
        Member member = new Member();
        member.setName("Jiwon"); // 데이터 설정

        repository.save(member); // 데이터 저장

        // 리턴 타입 : Optional ->  get()으로 꺼내야 된다.
        Member result = repository.findById(member.getId()).get(); // 멤버를 저장할때 아이디가 생성 된다. 데이터 검증

        // 검증 방법 : 객체(member)에서 꺼낸 값 == DB에서 꺼낸 값(store) 이면 검증 성공
        // System.out.println("result = " + (result == member));
        // Assertions.assertEquals(member, result); // (기대하는값, 나온값) // JUnit
        // option + enter 로 static import
        assertThat(member).isEqualTo(result); // assertj member 와 result 가 같다.
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("TomTom");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("TomTom2");
        repository.save(member2);

        Member result = repository.findByName("TomTom2").get();
        assertThat(result).isEqualTo(member2);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("TomTom");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("TomTom2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);

    }
}
