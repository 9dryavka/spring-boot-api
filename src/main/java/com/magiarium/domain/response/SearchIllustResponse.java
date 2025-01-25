package com.magiarium.domain.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class SearchIllustResponse {

    private Long total;

    private List<ImageUrlInfo> imageUrlInfos;

    @Data
    @Builder
    public static class ImageUrlInfo {
        String resourceUrl;
    }

}
