package com.magiarium.domain.dto.thumnail_item;

import com.magiarium.domain.enums.ItemTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class ItemInfo {
    Long itemId;

    ItemTypeEnum itemType;

    String itemTitle;
    String itemDescription;

    Timestamp itemCreatedAt;
    Timestamp itemUpdatedAt;

    String itemGroupName;

    List<String> itemTags;

    List<ItemContentInfo> contents;
}
