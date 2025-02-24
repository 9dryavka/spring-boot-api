package com.magiarium.controller;

import com.magiarium.domain.request.PostCommentRequest;
import com.magiarium.domain.response.GetLatestCommentResponse;
import com.magiarium.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    PostCommentService postCommentService;
    
    @PostMapping("/post")
    public ResponseEntity<Object> post(@RequestBody PostCommentRequest request) {

        postCommentService.post(request);


        return ResponseEntity.ok("OK");
    }
}
