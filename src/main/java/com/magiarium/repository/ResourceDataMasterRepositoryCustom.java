package com.magiarium.repository;

import com.magiarium.domain.entity.ContentMaster;
import com.magiarium.domain.entity.ResourceMaster;

import java.util.List;

public interface ResourceDataMasterRepositoryCustom {

    /**
     * コンテンツIDに紐付くリソースデータリストを取得する
     *
     * @param contentId コンテンツID
     * @param limit     取得件数
     * @param offset    オフセット
     * @return リソースIDのリスト
     */
    List<ResourceMaster> findResourceIdByContentId(Long contentId, Integer limit, Integer offset);

    /**
     * コンテンツIDに紐付くリソースデータの総数を取得する
     *
     * @param contentId コンテンツID
     * @return リソースデータの総数
     */
    Long countByContentId(Long contentId);

}
