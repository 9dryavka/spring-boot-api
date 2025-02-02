package com.magiarium.domain.response;

import com.magiarium.domain.dto.thread.ThreadInfo;
import com.magiarium.domain.entity.ThreadComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetThreadCommentResponse {

    Long threadId;
    String threadTitle;

    List<ThreadComment> comments;
}
