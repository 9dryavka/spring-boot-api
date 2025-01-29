package com.magiarium.repository.content_master;

import com.magiarium.domain.enums.ContentTypeEnum;
import com.magiarium.domain.dto.ContentMasterWithItemId;

import java.util.List;

public interface ContentMasterRepositoryCustom {

    /**
     * アイテムIDリストに一致するコンテンツマスタ情報を取得する
     *
     * @param contentType コンテンツタイプ
     * @param itemIdList  アイテムIDリスト
     * @return アイテムID付きのコンテンツマスタリスト
     */
    List<ContentMasterWithItemId> findByContentTypeAndItemIdInWithItemId(ContentTypeEnum contentType, List<Long> itemIdList);

}
