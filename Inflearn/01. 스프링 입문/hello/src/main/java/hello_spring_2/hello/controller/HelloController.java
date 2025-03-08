package hello_spring_2.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-string")
    @ResponseBody // http 프로토콜에서 body부에 직접 내용을 넣겠다.  view없이(viewResolver 없이)
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    ////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){  // Json ({"Key" : "value"}) 반환
        Hello hello = new Hello();
        hello.setName(name);

        return hello;
    }


    public static class Hello{
        private String name;

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
