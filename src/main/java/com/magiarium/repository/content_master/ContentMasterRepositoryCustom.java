package com.magiarium.repository.content_master;

import com.magiarium.domain.data.ItemTypeEnum;
import com.magiarium.domain.data.ContentTypeEnum;
import com.magiarium.domain.entity.ContentMaster;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContentMasterRepositoryCustom {

    /**
     * コンテンツ情報の総件数取得処理
     *
     * @param itemType    アイテムタイプ
     * @param category    カテゴリ
     * @param tagList     タグリスト
     * @param searchQuery 検索文字列
     * @return コンテンツマスタリスト
     */
    Long countByCategoryAndTagAndItemTypeAndContentType(
            ItemTypeEnum itemType,
            String category,
            List<String> tagList,
            String searchQuery,
            ContentTypeEnum contentType
    );

    /**
     * コンテンツID一覧の検索処理
     *
     * @param itemType    アイテムタイプ
     * @param category    カテゴリ
     * @param tagList     タグリスト
     * @param searchQuery 検索文字列
     * @param pageable    ページネーション情報
     * @return コンテンツマスタリスト
     */
    List<ContentMaster> findContentIdByCategoryAndTagAndItemTypeAndContentType(
            ItemTypeEnum itemType,
            String category,
            List<String> tagList,
            String searchQuery,
            ContentTypeEnum contentType,
            Pageable pageable
    );

}
