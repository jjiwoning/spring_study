package jiwon.jiwonspring;

import jiwon.jiwonspring.aop.TimeTraceAop;
import jiwon.jiwonspring.repository.*;
import jiwon.jiwonspring.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

// 자바에서 직접 스프링 빈 등록하는 방법

@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    }
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em){
//        this.em = em;
//    }

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){

        return new MemberService(memberRepository);
    }

//    @Bean
//    public TimeTraceAop timeTraceAop(){
//        return new TimeTraceAop();
//    }


    //@Bean
    //public MemberRepository memberRepository(){
        //return new JdbcMemberRepository(dataSource);
        //return new MemoryMemberRepository();
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);

    //}
}
