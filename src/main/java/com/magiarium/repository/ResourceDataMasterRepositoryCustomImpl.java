package com.magiarium.repository;

import com.magiarium.domain.entity.ContentMaster;
import com.magiarium.domain.entity.ContentResource;
import com.magiarium.domain.entity.ResourceMaster;
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
    public List<ResourceMaster> findResourceIdByPageId(Long pageId, Integer limit, Integer offset) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ResourceMaster> query = cb.createQuery(ResourceMaster.class);
        Root<ContentMaster> contentRoot = query.from(ContentMaster.class);
        Join<ContentMaster, ContentResource> contentResourceJoin = contentRoot.join("contentId", JoinType.INNER);
        Join<ContentResource, ResourceMaster> resourceDataMasterJoin = contentResourceJoin.join("resourceId", JoinType.INNER);

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
        Root<ContentMaster> contentRoot = query.from(ContentMaster.class);
        Join<ContentMaster, ContentResource> contentResourceJoin = contentRoot.join("contentId", JoinType.INNER);
        Join<ContentResource, ResourceMaster> resourceDataMasterJoin = contentResourceJoin.join("resourceId", JoinType.INNER);

        query.select(cb.count(resourceDataMasterJoin))
                .where(cb.equal(contentRoot.get("pageId"), pageId));

        return entityManager.createQuery(query).getSingleResult();
    }
}
