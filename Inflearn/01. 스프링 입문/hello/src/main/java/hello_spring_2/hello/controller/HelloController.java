package hello_spring_2.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
         model.addAttribute("data", "hello!!"); // data는 thymeleaf에 data (templates - hello.html <- 리턴)
         return "hello"; // <- templates 의 hello.html을 호출하는 거임
        // 컨트롤러에서 리턴값으로 문자를 반환하면 viewResolver가 화면을 찾아서 처리한다.
        // resources:templates/ ' + {ViewName} + '.html'을 찾음
    }

    // localhost:8080/hello-mvc?name=spring!!!
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";

    }



}
