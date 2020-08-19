package com.kichen.observer.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Just For Fun
 */
@RestController
public class IndexController {

    @GetMapping
    public String index() {
        return "Hello, boyz!";
    }

}


