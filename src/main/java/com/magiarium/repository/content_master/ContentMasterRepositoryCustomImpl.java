package com.magiarium.repository.content_master;

import com.magiarium.domain.enums.ContentTypeEnum;
import com.magiarium.domain.dto.ContentMasterWithItemId;
import com.magiarium.domain.entity.ItemContentRelation;
import com.magiarium.domain.entity.ItemMaster;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class ContentMasterRepositoryCustomImpl implements ContentMasterRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * アイテムIDリストに一致するコンテンツマスタ情報を取得する
     *
     * @param contentType コンテンツタイプ
     * @param itemIdList  アイテムIDリスト
     * @return アイテムID付きのコンテンツマスタリスト
     */
    @Override
    public List<ContentMasterWithItemId> findByContentTypeAndItemIdInWithItemId(ContentTypeEnum contentType, List<Long> itemIdList) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ContentMasterWithItemId> query = cb.createQuery(ContentMasterWithItemId.class);
        Root<ItemMaster> itemRoot = query.from(ItemMaster.class);
        Join<ItemMaster, ItemContentRelation> itemContentRelationJoin = itemRoot.join("itemContentRelations", jakarta.persistence.criteria.JoinType.INNER);

        query.select(cb.construct(ContentMasterWithItemId.class,
                        itemRoot.get("id"),
                        itemContentRelationJoin.get("content").get("id"),
                        itemContentRelationJoin.get("content").get("contentType"),
                        itemContentRelationJoin.get("content").get("label"),
                        itemContentRelationJoin.get("content").get("description"),
                        itemContentRelationJoin.get("content").get("contentJson"),
                        itemContentRelationJoin.get("content").get("createdAt"),
                        itemContentRelationJoin.get("content").get("updatedAt")
                ))
                .where(
                        cb.and(
                                itemRoot.get("id").in(itemIdList),
                                cb.equal(itemContentRelationJoin.get("content").get("contentType"), contentType)
                        )
                );

        return entityManager.createQuery(query).getResultList();

    }

}
