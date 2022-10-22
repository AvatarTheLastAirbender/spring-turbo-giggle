package com.giggle.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    @GetMapping(path = "/")
    public String helloWorld() {
        return "Welcome to Turbo initializer";
    }
}
