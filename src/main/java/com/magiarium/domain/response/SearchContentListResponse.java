package com.magiarium.domain.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.magiarium.domain.data.ResourceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@Builder
public class SearchContentListResponse {

    private Long total;

    private List<ContentInfo> contentList;

    @Data
    @Builder
    public static class ContentInfo {
        String title;
        String description;
        String contentJson;

        String category;
        List<String> tags;

        List<ResourceInfo> resourceInfos;

        @Data
        @Builder
        public static class ResourceInfo {
            Long resourceId;
            ResourceTypeEnum resourceTypeEnum;
            String resourceUrl;
        }

        Timestamp createdAt;
        Timestamp updatedAt;
    }
}
