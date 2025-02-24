package com.magiarium.domain.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.magiarium.domain.enums.ItemGroupTypeEnum;
import com.magiarium.domain.enums.OrderByTypeEnum;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class SearchThumbnailListRequest {

    // 検索条件
    String searchQuery;

    // カテゴリ種別
    ItemGroupTypeEnum categoryType;

    // カテゴリ
    String category;

    // 作者
    String author;

    // タグ
    List<String> tags;

    // 並び順
    OrderByTypeEnum orderBy;

    // 検索上限
    @Min(1)
    @Max(100)
    Integer limit = 10;

    // オフセット
    Integer offset = 0;
}
