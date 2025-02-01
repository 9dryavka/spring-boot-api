package com.magiarium.domain.response;

import com.magiarium.domain.dto.thumnail_item.ItemContentInfo;
import com.magiarium.domain.enums.ItemTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetMainContentResponse {
    Long itemId;

    ItemTypeEnum itemType;

    String itemTitle;
    String itemDescription;

    Timestamp itemCreatedAt;
    Timestamp itemUpdatedAt;

    String itemGroupName;

    List<String> itemTags;

    ItemContentInfo content;

}
