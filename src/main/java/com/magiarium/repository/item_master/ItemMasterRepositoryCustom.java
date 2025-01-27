package com.magiarium.repository.item_master;

import com.magiarium.domain.data.ItemGroupTypeEnum;
import com.magiarium.domain.data.ItemTypeEnum;
import com.magiarium.domain.data.OrderByTypeEnum;
import com.magiarium.domain.dto.ItemMasterWithCategoryAndView;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemMasterRepositoryCustom {

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
    Long countByClientSearch(
            ItemTypeEnum itemType,
            ItemGroupTypeEnum groupType,
            String groupName,
            List<String> tagList,
            String searchQuery
    );

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
    List<ItemMasterWithCategoryAndView> findByClientSearch(
            ItemTypeEnum itemType,
            ItemGroupTypeEnum groupType,
            String groupName,
            List<String> tagList,
            String searchQuery,
            OrderByTypeEnum orderBy,
            Pageable pageable
    );
}
