package com.magiarium.repository;

import com.magiarium.domain.entity.Content;
import com.magiarium.domain.entity.ContentResource;
import com.magiarium.domain.entity.ResourceDataMaster;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import java.util.List;

public class ResourceDataMasterRepositoryCustomImpl implements ResourceDataMasterRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * ページIDに紐付くリソースデータのリストを取得する
     *
     * @param pageId ページID
     * @return リソースIDのリスト
     */
    @Override
    public List<ResourceDataMaster> findResourceIdByPageId(Long pageId, Integer limit, Integer offset) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ResourceDataMaster> query = cb.createQuery(ResourceDataMaster.class);
        Root<Content> contentRoot = query.from(Content.class);
        Join<Content, ContentResource> contentResourceJoin = contentRoot.join("contentId", JoinType.INNER);
        Join<ContentResource, ResourceDataMaster> resourceDataMasterJoin = contentResourceJoin.join("resourceId", JoinType.INNER);

        query.select(resourceDataMasterJoin)
                .where(cb.equal(contentRoot.get("pageId"), pageId));

        return entityManager.createQuery(query)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

    }

    @Override
    public Long countByPageId(Long pageId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Content> contentRoot = query.from(Content.class);
        Join<Content, ContentResource> contentResourceJoin = contentRoot.join("contentId", JoinType.INNER);
        Join<ContentResource, ResourceDataMaster> resourceDataMasterJoin = contentResourceJoin.join("resourceId", JoinType.INNER);

        query.select(cb.count(resourceDataMasterJoin))
                .where(cb.equal(contentRoot.get("pageId"), pageId));

        return entityManager.createQuery(query).getSingleResult();
    }
}
