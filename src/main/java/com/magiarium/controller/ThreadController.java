package com.magiarium.controller;

import com.magiarium.domain.enums.ItemTypeEnum;
import com.magiarium.domain.request.SearchThumbnailContentListRequest;
import com.magiarium.domain.response.SearchThumbnailContentListResponse;
import com.magiarium.exception.NotFoundException;
import com.magiarium.service.SearchThumbnailItemListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class ThreadController {

    @Autowired
    private SearchThumbnailItemListService searchIllustListService;

    @GetMapping("/search")
    public ResponseEntity<Object> searchContentList(@Valid SearchThumbnailContentListRequest request) {
        SearchThumbnailContentListResponse response;
        try {
            response = searchIllustListService.search(ItemTypeEnum.THREAD, request);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.ok(response);

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
