package com.magiarium.domain.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.magiarium.domain.data.ContentTypeEnum;
import com.magiarium.domain.data.ItemTypeEnum;
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

    private List<ItemContentInfo> itemList;

    @Data
    @Builder
    public static class ItemContentInfo {

        Long itemId;

        ItemTypeEnum itemType;

        String itemTitle;
        String itemDescription;

        Timestamp itemCreatedAt;
        Timestamp itemUpdatedAt;

        String itemCategory;

        List<String> itemTags;

        List<ContentInfo> contents;

        @Data
        @Builder
        public static class ContentInfo {

            Long contentId;

            ContentTypeEnum contentType;

            String contentLabel;
            String contentDescription;

            String contentJson;

            Timestamp contentCreatedAt;
            Timestamp contentUpdatedAt;

            ResourceInfo resources;

            @Data
            @Builder
            public static class ResourceInfo {

                Long resourceId;
                String resourceLabel;
                String resourceType;
                String resourceDescription;
                String resourceUrl;
                Timestamp resourceCreatedAt;
                Timestamp resourceUpdatedAt;
            }

            Long itemReviews;
            List<ContentInfo> childContents;
        }

    }


}
