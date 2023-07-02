package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // static/index.html보다 이게 먼저 실행된다.
    // 순서가 controller 매핑 먼저 찾고 그다음에 static/index.html로 간다.
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
