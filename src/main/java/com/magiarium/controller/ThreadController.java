package com.magiarium.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class ThreadController {

    @GetMapping("/search")
    public String search() {
        return "search";
    }

    @GetMapping("")
    public String get() {
        return "index";
    }

    @PostMapping("/create")
    public String create() {
        return "create";
    }
}
