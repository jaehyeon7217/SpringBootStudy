package com.springboot.SpringBootCoreGuide.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/delete-api")
@Tag(name = "삭제 API", description = "Delete Controller입니다.")
public class DeleteController {

    // http://localhost:8080/api/v1/delete-api/{String 값}
    @Operation(summary = "Delete Path Variable", description = "Path Variable로 받아 Delete Mapping")
    @DeleteMapping("/{variable}")
    public String deleteVariable(@PathVariable String variable){
        return variable;
    }

    // http://localhost:8080/api/v1/delete-api/request1?email=xxx
    @DeleteMapping("/request1")
    public String deleteRequestParam(@RequestParam String email){
        return "e-mail : " + email;
    }

}
