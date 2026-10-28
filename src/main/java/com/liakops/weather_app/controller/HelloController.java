package com.liakops.weather_app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello API World!";
    }

    @GetMapping("/greet")
    public String greet(@RequestParam (defaultValue = "ξένε") String name) {
        return "Γεια σου " + name + "!";
    }
}
