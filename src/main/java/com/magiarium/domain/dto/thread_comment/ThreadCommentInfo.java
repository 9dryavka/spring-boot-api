package com.magiarium.domain.dto.thread_comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThreadCommentInfo {

    Long commentId;

    String userName;

    String comment;

    Timestamp createdAt;
    Timestamp updatedAt;

}
