package jiwon.jiwonspring.controller;

import jiwon.jiwonspring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import jiwon.jiwonspring.services.MemberService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 이거를 적으면 스프링 컨테이너에 아래의 클래스 객체를 생성해서 스프링빈으로 관리한다.
public class MemberController {

    private final MemberService memberService; // final로 두어 안전장치 설치
    // 스프링 컨테이너에 등록해서 쓰는게 좋기 때문에 여기서 new를 안하고 생성자로 만들어준다. -> why? 멤버서비스를 다른 컨트롤러에서도 쓸 수 있기 때문에
    // 하나만 만들어서 다 같이 쓰자

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") // 조회할 때 쓴다.
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // 데이터를 전달할때 쓴다.
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
