package com.magiarium.domain.dto.thumnail_item;

import com.magiarium.domain.enums.ResourceTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ItemContentResourceInfo {

    /* リソースID */
    Long resourceId;

    /* リソースラベル */
    String resourceLabel;

    /* リソース種別 */
    ResourceTypeEnum resourceType;

    /* リソースURL */
    String resourceUrl;

    /* 作成日時 */
    Timestamp resourceCreatedAt;

    /* 更新日時 */
    Timestamp resourceUpdatedAt;
}
