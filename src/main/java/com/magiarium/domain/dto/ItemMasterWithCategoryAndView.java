package com.magiarium.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemMasterWithCategoryAndView {

    Long id;

    String group;

    String title;

    String description;

    Long viewCount;

    Timestamp createdAt;

    Timestamp updatedAt;

}
