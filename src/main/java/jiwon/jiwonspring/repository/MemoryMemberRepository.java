package jiwon.jiwonspring.repository;

import jiwon.jiwonspring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository // 스프링 컨테이너에 해당 클래스를 등록한다.
public class MemoryMemberRepository implements MemberRepository { // 옵션 + 엔터

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 키 값을 생성해주는 변수

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id를 먼저 세팅해주고
        store.put(member.getId(), member); // 이를 store에 넣어준다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null이 리턴되는 경우가 생길 수 있어서 Optional로 감싸준다.
        //Optional.ofNullable()로 감싸서 리턴하면 null이여도 감싸서 리턴할 수 있다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 람다식 사용
        return store.values().stream() // value값을 루프 돌림
                .filter(member -> member.getName().equals((name))) // 필터링을 하는데 파라미터의 name과 같은지를 필터링
                .findAny(); // findAny() 하나라도 찾으면
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear(); // store를 지우는 함수
    }
}
