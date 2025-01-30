package com.magiarium.domain.dto.thumnail_item;

import com.magiarium.domain.enums.ContentTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class ItemContentInfo {

    Long contentId;

    ContentTypeEnum contentType;

    String contentLabel;
    String contentDescription;

    String contentJson;

    Timestamp contentCreatedAt;
    Timestamp contentUpdatedAt;

    ItemContentResourceInfo resources;

    Long itemReviews;
    List<ItemContentInfo> childContents;

}
