package jiwon.jiwonspring.repository;

import jiwon.jiwonspring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    // 어떤 DB를 사용하던 필요한 기능들을 인터페이스에 구현

    Member save(Member member); // 회원 정보 저장.
    // Optional -> null 값이 나오게 될 수도 있다. 이때 Optional로 감싸서 리턴해주는게 좋다
    Optional<Member> findById(Long id); // id로 찾기
    Optional<Member> findByName(String name); // 이름으로 찾기
    List<Member> findAll(); // 모두 찾기
}
