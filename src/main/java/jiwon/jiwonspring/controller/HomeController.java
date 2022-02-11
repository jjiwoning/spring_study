package jiwon.jiwonspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 이게 index 보다 우선순위가 위에 있다.
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
