package com.magiarium.domain.dto;

import com.magiarium.domain.enums.ContentTypeEnum;
import com.magiarium.domain.enums.ResourceTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

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
