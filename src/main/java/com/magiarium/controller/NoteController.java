package com.magiarium.controller;

import com.magiarium.domain.enums.ItemTypeEnum;
import com.magiarium.domain.request.SearchThumbnailContentListRequest;
import com.magiarium.domain.response.SearchThumbnailContentListResponse;
import com.magiarium.service.SearchContentListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private SearchContentListService searchIllustListService;

    @GetMapping("/search")
    public ResponseEntity<SearchThumbnailContentListResponse> searchContentList(SearchThumbnailContentListRequest request) {

        SearchThumbnailContentListResponse response = searchIllustListService.search(ItemTypeEnum.NOTE, request);

        return ResponseEntity.ok(response);

    }

}
