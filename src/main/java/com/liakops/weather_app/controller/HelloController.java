package com.liakops.weather_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping
    public String hello(){
        return "Hello API World!";
    }

    @GetMapping
    public String greet(@RequestParam String name){
            return "Hi" + name;
    }
}
