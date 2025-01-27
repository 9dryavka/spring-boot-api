package com.magiarium.repository.content_master;

import com.magiarium.domain.data.ContentTypeEnum;
import com.magiarium.domain.dto.ContentMasterWithItemId;
import com.magiarium.domain.entity.ContentMaster;
import com.magiarium.domain.entity.ItemMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentMasterRepository extends JpaRepository<ItemMaster, Long>, ContentMasterRepositoryCustom {

    /**
     * アイテムIDリストに一致するコンテンツマスタ情報を取得する
     *
     * @param contentType コンテンツタイプ
     * @param itemIdList  アイテムIDリスト
     * @return コンテンツマスタリスト
     */
    List<ContentMasterWithItemId> findByContentTypeAndItemIdIn(ContentTypeEnum contentType, List<Long> itemIdList);

}
