package com.magiarium.domain.dto.thread;

import com.magiarium.domain.entity.ThreadComment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThreadInfo {

    Long threadId;
    String threadTitle;

    ThreadComment comments;

}
