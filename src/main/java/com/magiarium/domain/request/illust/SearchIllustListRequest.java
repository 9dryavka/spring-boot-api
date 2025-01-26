package com.magiarium.domain.request.illust;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class SearchIllustListRequest {

    String searchQuery;

    String category;

    List<String> tagList;

    Integer limit = 10;

    Integer offset = 0;

}
