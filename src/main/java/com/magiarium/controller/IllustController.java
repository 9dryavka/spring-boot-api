package com.magiarium.controller;

import com.magiarium.domain.request.IllustSearchRequest;
import com.magiarium.domain.response.IllustSearchIResponse;
import com.magiarium.service.illust.IllustSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/illust")
public class IllustController {

    @Autowired
    private IllustSearchService illustSearchService;

    @GetMapping("/search")
    public ResponseEntity<IllustSearchIResponse> search(IllustSearchRequest request) {

        return ResponseEntity.ok(illustSearchService.search(request));

    }

}
