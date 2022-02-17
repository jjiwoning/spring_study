package jiwon.jiwonspring;

import jiwon.jiwonspring.repository.JdbcMemberRepository;
import jiwon.jiwonspring.repository.MemberRepository;
import jiwon.jiwonspring.repository.MemoryMemberRepository;
import jiwon.jiwonspring.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

// 자바에서 직접 스프링 빈 등록하는 방법

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new JdbcMemberRepository(dataSource);
        //return new MemoryMemberRepository();
    }
}
