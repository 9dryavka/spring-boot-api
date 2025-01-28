package com.magiarium.repository.resource_master;

import com.magiarium.domain.dto.ResourceMasterWithContentId;
import com.magiarium.domain.entity.ContentResourceRelation;
import com.magiarium.domain.entity.ResourceMaster;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;

import java.util.List;

public class ResourceMasterRepositoryCustomImpl implements ResourceMasterRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * コンテンツIDに紐付くリソース情報リストを取得する
     *
     * @param contentIdList コンテンツIDリスト
     * @return リソース情報リスト
     */
    @Override
    public List<ResourceMasterWithContentId> findByContentIdIn(List<Long> contentIdList) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ResourceMasterWithContentId> query = cb.createQuery(ResourceMasterWithContentId.class);
        Root<ContentResourceRelation> contentRoot = query.from(ContentResourceRelation.class);
        Join<ContentResourceRelation, ResourceMaster> resourceDataMasterJoin = contentRoot.join("resource", JoinType.INNER);

        query.multiselect(contentRoot.get("content").get("id"), resourceDataMasterJoin)
                .where(contentRoot.get("content").get("id").in(contentIdList));

        return entityManager.createQuery(query).getResultList();

    }

}
