package com.magiarium.domain.dto;

import com.magiarium.domain.data.ContentTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
