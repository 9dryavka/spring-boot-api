package com.magiarium.domain.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.magiarium.domain.enums.ItemGroupTypeEnum;
import com.magiarium.domain.enums.OrderByTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class SearchThumbnailContentListRequest {

    String searchQuery;

    ItemGroupTypeEnum groupType;

    String groupName;

    List<String> tagList;

    OrderByTypeEnum orderBy;

    Integer limit = 10;

    Integer offset = 0;
}
