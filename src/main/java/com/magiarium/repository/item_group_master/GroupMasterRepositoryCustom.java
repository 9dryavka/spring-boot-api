package com.magiarium.repository.item_group_master;

import com.magiarium.domain.entity.ItemGroupMaster;

import java.util.List;

public interface GroupMasterRepositoryCustom {

    /**
     * アイテムIDリストに一致するカテゴリ情報を取得する
     *
     * @param itemIdList アイテムIDリスト
     * @return カテゴリ情報リスト
     */
    List<ItemGroupMaster> findByItemIdIn(List<Long> itemIdList);
}
