package com.magiarium.domain.dto.thumnail_item;

import com.magiarium.domain.enums.ContentTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
public class ItemContentInfo {

    /* コンテンツID */
    Long contentId;

    /* 種別 */
    ContentTypeEnum contentType;

    /* タイトル */
    String contentLabel;

    /* 説明 */
    String contentDescription;

    /* コンテンツJSON */
    String contentJson;

    /* 作成日時 */
    Timestamp contentCreatedAt;
    /* 更新日時 */
    Timestamp contentUpdatedAt;

    /* リソースリスト */
    List<ItemContentResourceInfo> resources;

    /* 子コンテンツリスト */
    List<ItemContentInfo> childContents;

}
