package com.magiarium.controller;

import com.magiarium.domain.request.SearchIllustRequest;
import com.magiarium.domain.response.SearchIllustResponse;
import com.magiarium.service.illust.SearchIllustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/illust")
public class IllustController {

    @Autowired
    private SearchIllustService searchIllustService;

    @GetMapping("/search")
    public ResponseEntity<SearchIllustResponse> search(SearchIllustRequest request) {

        return ResponseEntity.ok(searchIllustService.search(request));

    }

}
