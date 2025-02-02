package com.magiarium.controller;

import com.magiarium.domain.request.PostCommentRequest;
import com.magiarium.domain.response.GetLatestCommentResponse;
import com.magiarium.service.GetLatestCommentService;
import com.magiarium.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    GetLatestCommentService getLatestCommentService;
    @Autowired
    PostCommentService postCommentService;

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
    public ResponseEntity<Object> post(@RequestBody PostCommentRequest request) {

        postCommentService.post(request);


        return ResponseEntity.ok("OK");
    }
}
