package com.magiarium.repository.item_master;

import com.magiarium.domain.enums.ItemGroupTypeEnum;
import com.magiarium.domain.enums.ItemTypeEnum;
import com.magiarium.domain.enums.OrderByTypeEnum;
import com.magiarium.domain.dto.item_master.ItemMasterWithCategoryAndView;
import com.magiarium.domain.entity.ItemGroupRelation;
import com.magiarium.domain.entity.ItemMaster;
import com.magiarium.domain.entity.ItemTagRelation;
import com.magiarium.repository.item_group_master.GroupMasterRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class ItemMasterRepositoryCustomImpl implements ItemMasterRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * クライアントの検索条件に基づいて、アイテム情報の総件数を取得する
     *
     * @param itemType    アイテムタイプ
     * @param groupType   グループ種別
     * @param groupName   グループ名
     * @param tagList     タグリスト
     * @param searchQuery 検索文字列
     * @return アイテム情報総件数
     */
    @Override
    public Long countByClientSearch(
            ItemTypeEnum itemType,
            ItemGroupTypeEnum groupType,
            String groupName,
            List<String> tagList,
            String searchQuery
    ) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<ItemMaster> itemRoot = countQuery.from(ItemMaster.class);

        List<Predicate> predicates = new ArrayList<>();
        // グループが指定されている場合、グループ情報を絞り込む
        if (ObjectUtils.isNotEmpty(groupType) && StringUtils.isNotEmpty(groupName)) {
            Root<ItemGroupRelation> groupRoot = countQuery.from(ItemGroupRelation.class);
            Join<ItemGroupRelation, GroupMasterRepository> groupJoin = groupRoot.join("group", JoinType.INNER);
            predicates.add(cb.and(
                    cb.equal(groupJoin.get("groupType"), groupType),
                    cb.equal(groupJoin.get("groupName"), groupName)
            ));
        }
        // タグが指定されている場合、タグ情報を絞り込む
        if (ObjectUtils.isNotEmpty(tagList)) {
            Join<ItemMaster, ItemTagRelation> tagJoin = itemRoot.join("tag", JoinType.INNER);
            predicates.add(tagJoin.get("tag").in(tagList));

        }
        // 検索文字列が指定されている場合、タイトル情報を絞り込む
        if (StringUtils.isNotEmpty(searchQuery)) {
            predicates.add(cb.like(itemRoot.get("title"), "%" + searchQuery + "%"));
        }

        countQuery.select(cb.count(itemRoot))
                .where(
                        cb.and(
                                cb.equal(itemRoot.get("itemType"), itemType),
                                cb.and(predicates.toArray(new Predicate[0]))
                        )
                );

        return entityManager.createQuery(countQuery).getSingleResult();
    }

    /**
     * クライアントの検索条件に基づいて、アイテム情報の総件数を取得する
     *
     * @param itemType    アイテムタイプ
     * @param groupType   グループ種別
     * @param groupName   グループ名
     * @param tagList     タグリスト
     * @param searchQuery 検索文字列
     * @param orderBy     ソート順
     * @param pageable    ページング情報
     * @return アイテム情報総件数
     */
    @Override
    public List<ItemMasterWithCategoryAndView> findByClientSearch(
            ItemTypeEnum itemType,
            ItemGroupTypeEnum groupType,
            String groupName,
            List<String> tagList,
            String searchQuery,
            OrderByTypeEnum orderBy,
            Pageable pageable
    ) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemMasterWithCategoryAndView> query = cb.createQuery(ItemMasterWithCategoryAndView.class);
        Root<ItemMaster> itemRoot = query.from(ItemMaster.class);

        List<Predicate> predicates = new ArrayList<>();
        // グループが指定されている場合、グループ情報を絞り込む
        if (ObjectUtils.isNotEmpty(groupType) && StringUtils.isNotEmpty(groupName)) {
            Root<ItemGroupRelation> groupRoot = query.from(ItemGroupRelation.class);
            Join<ItemGroupRelation, GroupMasterRepository> groupJoin = groupRoot.join("group", JoinType.INNER);
            predicates.add(cb.and(
                    cb.equal(groupJoin.get("groupType"), groupType),
                    cb.equal(groupJoin.get("groupName"), groupName)
            ));
        }
        // タグが指定されている場合、タグ情報を絞り込む
        if (ObjectUtils.isNotEmpty(tagList)) {
            Join<ItemMaster, ItemTagRelation> tagJoin = itemRoot.join("tag", JoinType.INNER);
            predicates.add(tagJoin.get("tag").in(tagList));

        }
        // 検索文字列が指定されている場合、タイトル情報を絞り込む
        if (StringUtils.isNotEmpty(searchQuery)) {
            predicates.add(cb.like(itemRoot.get("title"), "%" + searchQuery + "%"));
        }

        query.select(cb.construct(ItemMasterWithCategoryAndView.class,
                        itemRoot.get("id"),
                        itemRoot.get("title"),
                        itemRoot.get("description"),
                        itemRoot.get("createdAt"),
                        itemRoot.get("updatedAt"),
                        itemRoot.get("itemGroupRelations").get("group").get("label"),
                        itemRoot.get("itemView").get("viewCount")
                ))
                .where(
                        cb.and(
                                cb.equal(itemRoot.get("itemType"), itemType),
                                cb.and(predicates.toArray(new Predicate[0]))
                        )
                );

        return entityManager.createQuery(query)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }
}
