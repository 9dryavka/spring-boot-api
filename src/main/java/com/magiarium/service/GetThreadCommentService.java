package com.magiarium.service;

import com.magiarium.domain.entity.Thread;
import com.magiarium.domain.entity.ThreadComment;
import com.magiarium.domain.response.GetThreadCommentResponse;
import com.magiarium.repository.thread.ThreadRepository;
import com.magiarium.repository.thread_comment.ThreadCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetThreadCommentService {

    @Autowired
    private ThreadRepository threadRepository;
    @Autowired
    private ThreadCommentRepository threadCommentRepository;


    public GetThreadCommentResponse get(Long itemId) {

        Thread thread = threadRepository.findByItemId(itemId);

        List<ThreadComment> threadComments = threadCommentRepository.findByThreadId(thread.getId());

        return GetThreadCommentResponse.builder()
                .threadId(thread.getId())
                .threadTitle(thread.getTitle())
                .comments(threadComments)
                .build();
    }

}
