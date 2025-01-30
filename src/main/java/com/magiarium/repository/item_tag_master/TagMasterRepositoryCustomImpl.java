package com.magiarium.repository.item_tag_master;

import com.magiarium.domain.dto.item_tag_master.ItemTagMasterWithItemId;
import com.magiarium.domain.entity.ItemMaster;
import com.magiarium.domain.entity.ItemTagRelation;
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
        Root<ItemMaster> itemRoot = query.from(ItemMaster.class);
        Join<ItemMaster, ItemTagRelation> itemTagRelationJoin = itemRoot.join("itemTagRelations", JoinType.INNER);

        query.select(cb.construct(ItemTagMasterWithItemId.class,
                itemRoot.get("id"),
                itemTagRelationJoin.get("tag").get("id"),
                itemTagRelationJoin.get("tag").get("label"))
        ).where(
                itemRoot.get("id").in(itemIdList)
        );

        return entityManager.createQuery(query).getResultList();

    }
}
