package com.magiarium.service;

import com.magiarium.domain.entity.ThreadComment;
import com.magiarium.domain.response.GetLatestCommentResponse;
import com.magiarium.repository.thread_comment.ThreadCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GetLatestCommentService {

    @Autowired
    private ThreadCommentRepository threadCommentRepository;

    public GetLatestCommentResponse getLatestComment() {
        List<ThreadComment> threadCommentList = threadCommentRepository.findTop50OrderByCreatedAtDesc();

        return GetLatestCommentResponse.builder()
                .latestCommentList(threadCommentList)
                .build();
    }
}
