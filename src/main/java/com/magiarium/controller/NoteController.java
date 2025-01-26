package com.magiarium.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/note")
public class NoteController {


    @GetMapping("/search")
    public String search() {
        return "note/search";
    }

    @GetMapping("")
    public String get() {
        return "note/index";
    }

}
