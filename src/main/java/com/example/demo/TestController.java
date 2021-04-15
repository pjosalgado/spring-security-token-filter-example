package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        return "Hello, " + name;
    }

    @GetMapping("/hello/pt")
    public String helloInPortuguese(@RequestParam String name) {
        return "Olá, " + name;
    }

    @GetMapping("/bonjour")
    public String helloInFrench(@RequestParam String name) {
        return "Bonjour, " + name;
    }

}
