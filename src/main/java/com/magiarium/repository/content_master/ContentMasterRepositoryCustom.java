package com.magiarium.repository.content_master;

import com.magiarium.domain.dto.content_master.ChildContent;
import com.magiarium.domain.dto.content_master.MainContentItem;
import com.magiarium.domain.enums.ContentTypeEnum;
import com.magiarium.domain.dto.content_master.ContentMasterWithItemId;

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


    /**
     * アイテムIDをもとに、一致するメインコンテンツ情報を取得
     *
     * @param itemId アイテムID
     * @return メインコンテンツ情報
     */
    MainContentItem getMainContentByItemId(Long itemId);

    /**
     * コンテンツIDをもとに、一致する子コンテンツ情報を取得
     *
     * @param contentId コンテンツID
     * @return 子コンテンツ情報リスト
     */
    List<ChildContent> getChildContentListByContentId(Long contentId);

}
