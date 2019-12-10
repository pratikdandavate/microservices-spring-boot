package com.aira.matrix.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@RestController
@RequestScope
public class HelloController {

    @Value("${msg}")
    private String msg;

    @GetMapping("/msg")
    public String sayHello() {
        return this.msg;
    }
}
