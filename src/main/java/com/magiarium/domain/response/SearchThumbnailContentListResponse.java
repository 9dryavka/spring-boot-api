package com.magiarium.domain.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.magiarium.domain.dto.thumnail_item.ItemInfo;
import com.magiarium.domain.enums.ContentTypeEnum;
import com.magiarium.domain.enums.ItemTypeEnum;
import com.magiarium.domain.enums.ResourceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@Builder
public class SearchThumbnailContentListResponse {

    private Long total;

    private List<ItemInfo> itemList;
    
}
