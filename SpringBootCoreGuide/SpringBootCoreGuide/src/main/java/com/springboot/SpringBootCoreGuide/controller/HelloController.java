package com.springboot.SpringBootCoreGuide.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);
    @RequestMapping("/hello")
    public String hello(){
        LOGGER.info("Info");
        LOGGER.debug("Debug");
        LOGGER.error("ERROR");
        return "Hello World!";
    }

}
