package com.magiarium.domain.dto.thumnail_item;

import com.magiarium.domain.enums.ResourceTypeEnum;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ItemContentResourceInfo {
    Long resourceId;
    String resourceLabel;
    ResourceTypeEnum resourceType;
    String resourceUrl;
    Timestamp resourceCreatedAt;
    Timestamp resourceUpdatedAt;
}
