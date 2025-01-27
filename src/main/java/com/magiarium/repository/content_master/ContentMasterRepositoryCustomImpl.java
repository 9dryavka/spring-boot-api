package com.magiarium.repository.content_master;

import com.magiarium.domain.data.ContentTypeEnum;
import com.magiarium.domain.dto.ContentMasterWithItemId;
import com.magiarium.domain.entity.ContentMaster;
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
        Root<ItemMaster> contentRoot = query.from(ItemMaster.class);
        Join<ItemMaster, ContentMaster> contentJoin = contentRoot.join("content", jakarta.persistence.criteria.JoinType.INNER);

        query.multiselect(contentRoot.get("id"), contentJoin)
                .where(
                        cb.and(
                                cb.equal(contentJoin.get("contentType"), contentType),
                                contentRoot.get("id").in(itemIdList)
                        )
                );

        return entityManager.createQuery(query).getResultList();

    }

}
