package com.magiarium.repository.content_master;

import com.magiarium.domain.dto.content_master.ChildContent;
import com.magiarium.domain.dto.content_master.MainContentItem;
import com.magiarium.domain.entity.*;
import com.magiarium.domain.enums.ContentAttributeTypeEnum;
import com.magiarium.domain.enums.ContentTypeEnum;
import com.magiarium.domain.dto.content_master.ContentMasterWithItemId;
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

    /**
     * アイテムIDをもとに、一致するメインコンテンツ情報を取得
     *
     * @param itemId アイテムID
     * @return メインコンテンツ情報
     */
    @Override
    public MainContentItem getMainContentByItemId(Long itemId) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MainContentItem> query = cb.createQuery(MainContentItem.class);
        Root<ItemMaster> itemRoot = query.from(ItemMaster.class);
        Join<ItemMaster, ItemContentRelation> itemContentRelationJoin
                = itemRoot.join("itemContentRelations", jakarta.persistence.criteria.JoinType.INNER);
        Join<ItemMaster, ItemView> itemViewJoin
                = itemRoot.join("itemView", jakarta.persistence.criteria.JoinType.INNER);
        Join<ItemMaster, ItemGroupRelation> itemGroupRelationJoin
                = itemRoot.join("itemGroupRelations", jakarta.persistence.criteria.JoinType.INNER);

        query.select(cb.construct(MainContentItem.class,
                        itemRoot.get("title"),
                        itemRoot.get("itemType"),
                        itemRoot.get("description"),
                        itemContentRelationJoin.get("createdAt"),
                        itemContentRelationJoin.get("updatedAt"),
                        itemGroupRelationJoin.get("group").get("label"),
                        itemViewJoin.get("viewCount"),
                        itemContentRelationJoin.get("content").get("id"),
                        itemContentRelationJoin.get("content").get("contentType"),
                        itemContentRelationJoin.get("content").get("label"),
                        itemContentRelationJoin.get("content").get("description"),
                        itemContentRelationJoin.get("content").get("contentJson")
                ))
                .where(
                        cb.and(
                                cb.equal(itemRoot.get("id"), itemId),
                                cb.equal(itemContentRelationJoin.get("content").get("contentType"), ContentTypeEnum.MAIN)
                        )
                );

        return entityManager.createQuery(query).getSingleResult();
    }

    /**
     * コンテンツIDをもとに、一致する子コンテンツ情報を取得
     *
     * @param contentId コンテンツID
     * @return 子コンテンツ情報リスト
     */
    @Override
    public List<ChildContent> getChildContentListByContentId(Long contentId) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ChildContent> query = cb.createQuery(ChildContent.class);
        Root<ContentMaster> itemRoot = query.from(ContentMaster.class);
        Join<ContentMaster, ContentAttribute> contentAttributeJoin
                = itemRoot.join("contentAttributes", jakarta.persistence.criteria.JoinType.INNER);

        query.select(cb.construct(ChildContent.class,
                        contentAttributeJoin.get("content").get("id"),
                        contentAttributeJoin.get("content").get("contentType"),
                        contentAttributeJoin.get("content").get("label"),
                        contentAttributeJoin.get("content").get("contentJson")
                ))
                .where(
                        cb.and(
                                cb.and(
                                        cb.equal(contentAttributeJoin.get("attributeType"), ContentAttributeTypeEnum.PARENT),
                                        cb.equal(contentAttributeJoin.get("content").get("id"), contentId)
                                ),
                                cb.equal(contentAttributeJoin.get("attributeType"), ContentAttributeTypeEnum.CHILD)
                        )
                );

        return entityManager.createQuery(query).getResultList();

    }


}
