package com.magiarium.repository.resource_master;

import com.magiarium.domain.entity.ContentResource;
import com.magiarium.domain.entity.ResourceMaster;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import java.util.List;

public class ResourceMasterRepositoryCustomImpl implements ResourceMasterRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * コンテンツIDに紐付くリソースデータのリストを取得する
     *
     * @param contentId コンテンツID
     * @return リソースIDのリスト
     */
    @Override
    public List<ResourceMaster> findResourceIdByContentId(Long contentId, Integer limit, Integer offset) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ResourceMaster> query = cb.createQuery(ResourceMaster.class);
        Root<ContentResource> contentRoot = query.from(ContentResource.class);
        Join<ContentResource, ResourceMaster> resourceDataMasterJoin = contentRoot.join("resource", JoinType.INNER);

        query.select(resourceDataMasterJoin)
                .where(cb.equal(contentRoot.get("content").get("id"), contentId));

        return entityManager.createQuery(query)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();

    }

    /**
     * コンテンツIDに紐付くリソースデータの総数を取得する
     *
     * @param contentId コンテンツID
     * @return リソースデータの総数
     */
    @Override
    public Long countByContentId(Long contentId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<ContentResource> contentRoot = query.from(ContentResource.class);
        Join<ContentResource, ResourceMaster> resourceMasterJoin = contentRoot.join("resource", JoinType.INNER);

        query.select(cb.count(resourceMasterJoin))
                .where(cb.equal(contentRoot.get("content").get("id"), contentId));

        return entityManager.createQuery(query).getSingleResult();
    }
}
