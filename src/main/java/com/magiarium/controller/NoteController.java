package com.magiarium.controller;

import com.magiarium.domain.enums.ItemTypeEnum;
import com.magiarium.domain.request.SearchThumbnailListRequest;
import com.magiarium.domain.response.GetMainContentResponse;
import com.magiarium.domain.response.SearchThumbnailListResponse;
import com.magiarium.exception.NotFoundException;
import com.magiarium.service.GetContentInfoService;
import com.magiarium.service.SearchThumbnailListService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private SearchThumbnailListService searchIllustListService;
    @Autowired
    private GetContentInfoService getContentInfoService;

    @GetMapping("/search")
    public ResponseEntity<Object> searchContentList(@Valid SearchThumbnailListRequest request) {
        SearchThumbnailListResponse response;
        try {
            response = searchIllustListService.search(ItemTypeEnum.NOTE, request);
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable String id) {

        GetMainContentResponse response;
        try {
            response = getContentInfoService.get(Long.parseLong(id));
        } catch (NotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

        return ResponseEntity.ok(response);

    }

}
