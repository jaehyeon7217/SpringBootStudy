package com.springboot.SpringBootCoreGuide.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delete-api")
public class DeleteController {

    // http://localhost:8080/api/v1/delete-api/{String 값}
    @DeleteMapping("/{variable}")
    public String deleteVariable(@PathVariable String variable){
        return variable;
    }

    // http://localhost:8080/api/v1/delete-api/request1
    @DeleteMapping("/request1")
    public String deleteRequestParam(@RequestParam String email){
        return "e-mail : " + email;
    }

}
