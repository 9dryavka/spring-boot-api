package com.magiarium.repository.item_tag_master;

import com.magiarium.domain.dto.ItemTagMasterWithItemId;
import com.magiarium.domain.entity.ItemMaster;
import com.magiarium.domain.entity.ItemTagMaster;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import java.util.List;

public class TagMasterRepositoryCustomImpl implements TagMasterRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * /**
     * アイテムIDリストに一致するタグ情報を取得する
     *
     * @param itemIdList アイテムIDリスト
     * @return タグ情報リスト
     */
    @Override
    public List<ItemTagMasterWithItemId> findByItemIdIn(List<Long> itemIdList) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemTagMasterWithItemId> query = cb.createQuery(ItemTagMasterWithItemId.class);
        Root<ItemTagMaster> tagRoot = query.from(ItemTagMaster.class);
        Join<ItemTagMaster, ItemMaster> itemTagJoin = tagRoot.join("itemTag", JoinType.INNER);

        query.multiselect(itemTagJoin.get("item").get("id"), tagRoot)
                .where(itemTagJoin.get("item").get("id").in(itemIdList));

        return entityManager.createQuery(query).getResultList();

    }
}
