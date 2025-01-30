package com.magiarium.domain.dto.resource_master;

import com.magiarium.domain.enums.ResourceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResourceMasterWithContentId {

    private Long contentId;
    private Long resourceId;
    private String releaseLabel; // コンテンツに紐付けた際の別名用
    private ResourceTypeEnum resourceType;
    private String resourceLabel;
    private String resourceUrl;

}
