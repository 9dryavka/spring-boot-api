package com.magiarium.controller;

import com.magiarium.domain.enums.ItemTypeEnum;
import com.magiarium.domain.request.SearchThumbnailListRequest;
import com.magiarium.domain.response.GetThreadCommentResponse;
import com.magiarium.domain.response.SearchThumbnailListResponse;
import com.magiarium.exception.NotFoundException;
import com.magiarium.service.GetThreadCommentService;
import com.magiarium.service.SearchThumbnailListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class ThreadController {

    @Autowired
    private SearchThumbnailListService searchIllustListService;
    @Autowired
    private GetThreadCommentService getThreadCommentService;

    @GetMapping("/search")
    public ResponseEntity<Object> searchContentList(@Valid SearchThumbnailListRequest request) {
        SearchThumbnailListResponse response;
        try {
            response = searchIllustListService.search(ItemTypeEnum.THREAD, request);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable Long id) {

        GetThreadCommentResponse response;
        try {
            response = getThreadCommentService.get(id);
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public String create() {
        return "create";
    }
}
