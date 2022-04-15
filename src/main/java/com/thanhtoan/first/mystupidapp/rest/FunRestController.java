package com.thanhtoan.first.mystupidapp.rest;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    // do stupid thing
    @GetMapping("/")
    public String sayHello() {
        return "Hello cc " + LocalDateTime.now();
    }
}
