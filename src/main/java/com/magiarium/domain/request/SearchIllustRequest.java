package com.magiarium.domain.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@Builder
public class SearchIllustRequest {

    String searchQuery;

    String category;

    List<String> tagList;

    @Builder.Default
    Integer limit = 10;

    @Builder.Default
    Integer offset = 0;

}
