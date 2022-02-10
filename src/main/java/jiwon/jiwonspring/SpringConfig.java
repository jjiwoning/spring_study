package jiwon.jiwonspring;

import jiwon.jiwonspring.repository.MemberRepository;
import jiwon.jiwonspring.repository.MemoryMemberRepository;
import jiwon.jiwonspring.services.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바에서 직접 스프링 빈 등록하는 방법

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
