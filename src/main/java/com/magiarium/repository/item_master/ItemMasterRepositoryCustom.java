package com.magiarium.repository.item_master;

import com.magiarium.domain.data.ContentTypeEnum;
import com.magiarium.domain.data.ItemTypeEnum;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemMasterRepositoryCustom {

    /**
     * アイテム情報の総件数取得処理
     *
     * @param itemType    アイテムタイプ
     * @param category    カテゴリ
     * @param tagList     タグリスト
     * @param searchQuery 検索文字列
     * @return アイテム情報の総件数
     */
    Long countByCategoryAndTagAndItemTypeAndContentType(
            ItemTypeEnum itemType,
            String category,
            List<String> tagList,
            String searchQuery,
            ContentTypeEnum contentType
    );

    /**
     * アイテムID一覧の検索処理
     *
     * @param itemType    アイテムタイプ
     * @param category    カテゴリ
     * @param tagList     タグリスト
     * @param searchQuery 検索文字列
     * @param pageable    ページネーション情報
     * @return アイテムID一覧
     */
    List<Long> findItemIdByCategoryAndTagAndItemTypeAndContentType(
            ItemTypeEnum itemType,
            String category,
            List<String> tagList,
            String searchQuery,
            ContentTypeEnum contentType,
            Pageable pageable
    );
}
