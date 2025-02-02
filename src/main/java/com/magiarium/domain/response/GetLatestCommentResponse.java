package com.magiarium.domain.response;

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
public class GetLatestCommentResponse {

    List<ThreadComment> latestCommentList;

}
