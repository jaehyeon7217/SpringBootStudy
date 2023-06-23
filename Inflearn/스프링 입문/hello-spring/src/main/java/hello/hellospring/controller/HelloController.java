package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!!");
        return "hello";

    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }
    // @ResponseBody가 없으면 ViewResolver를 호출하여 HTML을 반환

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }


    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    // ResponseBody -> HttpResponseBody에 내용을 그대로 담아서 전달해준다.
    // ResponseBody가 오면 HttpMessageConverter가 동작하여 객체면 JsonConverter 단순 문자열이면 StringConverter를 호출하여 반환
    // 기본 문자 처리 : StringHttpMessageConverter
    // 기본 객체 처리 : MappingJackson2HttpMessageConverter

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
