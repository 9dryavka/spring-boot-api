package com.magiarium.repository.item_group_master;

import com.magiarium.domain.entity.ItemGroupMaster;
import com.magiarium.domain.entity.ItemMaster;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import java.util.List;

public class GroupMasterRepositoryCustomImpl implements GroupMasterRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * アイテムIDリストに一致するカテゴリ情報を取得する
     *
     * @param itemIdList アイテムIDリスト
     * @return カテゴリ情報リスト
     */
    @Override
    public List<ItemGroupMaster> findByItemIdIn(List<Long> itemIdList) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemGroupMaster> query = cb.createQuery(ItemGroupMaster.class);
        Root<ItemGroupMaster> categoryRoot = query.from(ItemGroupMaster.class);
        Join<ItemGroupMaster, ItemMaster> itemCategoryJoin = categoryRoot.join("itemCategory", JoinType.INNER);

        query.select(categoryRoot)
                .where(itemCategoryJoin.get("item").get("id").in(itemIdList));

        return entityManager.createQuery(query).getResultList();

    }
}
