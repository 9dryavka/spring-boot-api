package com.magiarium.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/novel")
public class NovelController {

    @GetMapping("/search")
    public String search() {
        return "novel/search";
    }

    @GetMapping("")
    public String get() {
        return "novel/index";
    }
}
