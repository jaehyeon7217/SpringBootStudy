package com.springboot.SpringBootCoreGuide.controller;

import com.springboot.SpringBootCoreGuide.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    // http://localhost:8080/api/v1/post-api/domain
    @RequestMapping(value = "/domain", method = RequestMethod.POST)
    public String postExample(){
        return "hello Post API";
    }

    // http://localhost:8080/api/v1/post-api/member
    @PostMapping("/member")
    public String postMember(@RequestBody Map<String, Object> postData){
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach( map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    // http://localhost:8080/api/v1/post-api/member2
    @PostMapping("/member2")
    public String postMember2(@RequestBody MemberDto memberDto){
        return memberDto.toString();
    }


}
