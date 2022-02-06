package jiwon.jiwonspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    //API
    //템플릿 엔진과의 차이 -> view가 없다. 이 문자 그대로 내려감
    @GetMapping("hello-string")
    @ResponseBody // http에서의 body에 이 데이터를 넣어주겠다. http의 body에 응답하겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
    }

    // 실제로 더 자주 쓰는 API 방식의 코드
    // 이 코드로 하려는 것 : 데이터를 주려고 한다.
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // json 방식으로 리턴이 된다. -> spring은 default로 json 방식으로 객체 리턴해준다.
        // json -> key, value 형태
//        1. JSON 데이터는 이름과 값의 쌍으로 이루어집니다.
//        2. JSON 데이터는 쉼표(,)로 나열됩니다.
//        3. 객체(object)는 중괄호({})로 둘러쌓아 표현합니다.
//        4. 배열(array)은 대괄호([])로 둘러쌓아 표현합니다.
    }

    //객체 생성
    static class Hello{ // 스태틱 클래스 -> controller 클래스 내에서 사용가능 ex) HelloController.Hello 형태로
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
