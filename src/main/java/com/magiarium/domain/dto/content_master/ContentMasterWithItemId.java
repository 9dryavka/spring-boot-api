package com.magiarium.domain.dto.content_master;

import com.magiarium.domain.enums.ContentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentMasterWithItemId {

    Long itemId;

    Long contentId;

    ContentTypeEnum contentType;

    String label;

    String description;

    String contentJson;

    Timestamp createdAt;

    Timestamp updatedAt;

}
