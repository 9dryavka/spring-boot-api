package com.magiarium.controller;

import com.magiarium.domain.response.GetLatestCommentResponse;
import com.magiarium.service.GetLatestCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    GetLatestCommentService getLatestCommentService;

    @GetMapping("/latest")
    public ResponseEntity<GetLatestCommentResponse> latest() {
        GetLatestCommentResponse response;
        try {
            response = getLatestCommentService.getLatestComment();
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/post")
    public String post() {
        return "post";
    }
}
