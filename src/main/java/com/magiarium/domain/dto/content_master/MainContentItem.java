package com.magiarium.domain.dto.content_master;

import com.magiarium.domain.enums.ContentTypeEnum;
import com.magiarium.domain.enums.ItemTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainContentItem {

    String itemTitle;

    ItemTypeEnum itemType;

    String itemDescription;

    Timestamp itemCreatedAt;

    Timestamp itemUpdatedAt;

    String itemGroupName;

    Long itemViewCount;

    Long contentId;

    ContentTypeEnum contentType;

    String contentLabel;
    String contentDescription;

    String contentJson;

}
