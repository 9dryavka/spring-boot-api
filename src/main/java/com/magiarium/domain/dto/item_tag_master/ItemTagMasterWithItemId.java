package com.magiarium.domain.dto.item_tag_master;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemTagMasterWithItemId {

    private Long itemId;
    private Long tagId;
    private String label;

}
