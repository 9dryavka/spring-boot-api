package com.magiarium.domain.dto.thumnail_item;

import com.magiarium.domain.enums.ItemTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class ItemInfo {

    /* アイテムID */
    Long itemId;

    /* 種別 */
    ItemTypeEnum itemType;

    /* タイトル */
    String itemTitle;

    /* 説明 */
    String itemDescription;

    /* 作成日時 */
    Timestamp itemCreatedAt;

    /* 更新日時 */
    Timestamp itemUpdatedAt;

    /* グループ名 */
    String itemGroupName;

    /* タグリスト */
    List<String> itemTags;

    /* 閲覧数 */
    Long itemReviews;

    /* コンテンツリスト */
    List<ItemContentInfo> contents;
}
