package jiwon.jiwonspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // 스프링에서는 컨트롤러의 어노테이션을 적어주어야 한다.
public class HelloController {

    @GetMapping("hello") // -> 이거를 해주면 웹 어플리케이션에서 /hello라고 들어오면 해당 메서드를 호출해준다.
    public String hello(Model model){
        model.addAttribute("data", "hello!!"); // 키, 밸류 순서로 매개변수 대입
        return "hello"; // 해당 이름을 가지는 템플릿 파일의 html로 보내준다.
    }

    // 외부에서 파라미터를 받으면 @RequestParam 어노테이션을 적어준다.
    //@RequestParam(value = "name" , required = false)
    // required가 true면 매개변수를 무조건 넘거야되고 false면 안넘겨도 된다. -> required의 디폴트는 true
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name); // 키, 밸류 순서 밸류의 name은 외부에서 받은 파라미터
        return "hello-template";
    }
}
