package com.magiarium.domain.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class IllustSearchRequest {

    String searchQuery;

    String category;

    List<String> tagList;

    Integer limit = 10;

    Integer offset = 0;

}
