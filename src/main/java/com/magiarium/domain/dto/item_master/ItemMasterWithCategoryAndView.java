package com.magiarium.domain.dto.item_master;

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

    String title;

    String description;

    Timestamp createdAt;

    Timestamp updatedAt;

    String groupName;

    Long viewCount;

}
