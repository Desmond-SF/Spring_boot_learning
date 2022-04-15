package com.thanhtoan.first.idiot.rest;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {
    // do stupid thing
    @Value("${team.name}")
    private String teamName;

    @GetMapping("/")
    public String sayHello() {
        return "Hello cc " + teamName + "  " + LocalDateTime.now();
    }

    // expose a new endpoint for "workout"
    @GetMapping("/workout")
    public String getDailyWorkout() {
        return "RUN RUN RUN!";
    }

    // expose new endpoint

    @GetMapping("/workout/fortune")
    public String getDailyFortune() {
        return "Today is a stupid day";
    }
}
