package com.magiarium.repository.item_tag_master;

import com.magiarium.domain.dto.item_tag_master.ItemTagMasterWithItemId;

import java.util.List;

public interface TagMasterRepositoryCustom {

    /**
     * アイテムIDリストに一致するタグ情報を取得する
     *
     * @param itemIdList アイテムIDリスト
     * @return タグ情報リスト
     */
    List<ItemTagMasterWithItemId> findByItemIdIn(List<Long> itemIdList);
}
