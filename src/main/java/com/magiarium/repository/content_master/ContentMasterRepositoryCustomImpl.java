package com.magiarium.repository.content_master;

import com.magiarium.domain.data.ContentTypeEnum;
import com.magiarium.domain.data.ItemTypeEnum;
import com.magiarium.domain.entity.ContentMaster;
import com.magiarium.domain.entity.ItemCategory;
import com.magiarium.domain.entity.ItemMaster;
import com.magiarium.domain.entity.ItemTag;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ContentMasterRepositoryCustomImpl implements ContentMasterRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * コンテンツ情報の総件数取得処理
     *
     * @param itemType    アイテムタイプ
     * @param category    カテゴリ
     * @param tagList     タグリスト
     * @param searchQuery 検索文字列
     * @return 総件数
     */
    @Override
    public Long countByCategoryAndTagAndItemTypeAndContentType(
            ItemTypeEnum itemType,
            String category,
            List<String> tagList,
            String searchQuery,
            ContentTypeEnum contentType
    ) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<ItemMaster> itemRoot = countQuery.from(ItemMaster.class);
        Join<ItemMaster, ItemTag> tagJoin = itemRoot.join("tag", JoinType.INNER);
        Join<ItemMaster, ItemCategory> categoryJoin = itemRoot.join("category", JoinType.INNER);
        Join<ItemMaster, ContentMaster> contentJoin = itemRoot.join("content", JoinType.INNER);

        countQuery.select(cb.count(contentJoin))
                .where(
                        cb.and(
                                cb.and(
                                        cb.equal(categoryJoin.get("itemType"), itemType),
                                        cb.equal(contentJoin.get("category"), category)
                                ),
                                cb.and(
                                        cb.equal(contentJoin.get("itemType"), itemType),
                                        tagJoin.get("tag").in(tagList)
                                ),
                                cb.and(
                                        cb.equal(contentJoin.get("contentType"), contentType),
                                        cb.like(contentJoin.get("title"), "%" + searchQuery + "%")
                                )
                        )
                )
                .orderBy(cb.desc(contentJoin.get("createdAt")));


        return entityManager.createQuery(countQuery).getSingleResult();
    }

    /**
     * コンテンツ情報の検索処理
     *
     * @param itemType    アイテムタイプ
     * @param category    カテゴリ
     * @param tagList     タグリスト
     * @param searchQuery 検索文字列
     * @param pageable    ページネーション情報
     * @return コンテンツマスタリスト
     */
    @Override
    public List<ContentMaster> findContentIdByCategoryAndTagAndItemTypeAndContentType(
            ItemTypeEnum itemType,
            String category,
            List<String> tagList,
            String searchQuery,
            ContentTypeEnum contentType,
            Pageable pageable
    ) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ContentMaster> query = cb.createQuery(ContentMaster.class);
        Root<ItemMaster> itemRoot = query.from(ItemMaster.class);
        Join<ItemMaster, ItemTag> tagJoin = itemRoot.join("tag", JoinType.INNER);
        Join<ItemMaster, ItemCategory> categoryJoin = itemRoot.join("category", JoinType.INNER);
        Join<ItemMaster, ContentMaster> contentJoin = itemRoot.join("content", JoinType.INNER);

        query.select(contentJoin)
                .where(
                        cb.and(
                                cb.and(
                                        cb.equal(categoryJoin.get("itemType"), itemType),
                                        cb.equal(contentJoin.get("category"), category)
                                ),
                                cb.and(
                                        cb.equal(contentJoin.get("itemType"), itemType),
                                        tagJoin.get("tag").in(tagList)
                                ),
                                cb.and(
                                        cb.equal(contentJoin.get("contentType"), contentType),
                                        cb.like(contentJoin.get("title"), "%" + searchQuery + "%")
                                )
                        )
                )
                .orderBy(cb.desc(contentJoin.get("createdAt")));

        return entityManager.createQuery(query)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }
}
