package com.magiarium.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @GetMapping("/latest")
    public String latest() {
        return "latest";
    }

    @GetMapping("/get")
    public String get() {
        return "get";
    }

    @PostMapping("/post")
    public String post() {
        return "post";
    }
}
