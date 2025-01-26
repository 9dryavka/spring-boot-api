package com.magiarium.controller;

import com.magiarium.domain.request.illust.SearchIllustListRequest;
import com.magiarium.domain.response.illust.SearchIllustListResponse;
import com.magiarium.service.illust.SearchIllustListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/illust")
public class IllustController {

    @Autowired
    private SearchIllustListService searchIllustListService;

    @GetMapping("/search")
    public ResponseEntity<SearchIllustListResponse> search(SearchIllustListRequest request) {

        return ResponseEntity.ok(searchIllustListService.search(request));

    }

}
