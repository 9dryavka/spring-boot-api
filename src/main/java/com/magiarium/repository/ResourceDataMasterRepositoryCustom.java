package com.magiarium.repository;

import com.magiarium.domain.entity.ResourceDataMaster;

import java.util.List;

public interface ResourceDataMasterRepositoryCustom {

    /**
     * ページIDに紐付くリソースデータリストを取得する
     *
     * @param pageId ページID
     * @param limit  取得件数
     * @param offset オフセット
     * @return リソースIDのリスト
     */
    List<ResourceDataMaster> findResourceIdByPageId(Long pageId, Integer limit, Integer offset);

    /**
     * ページIDに紐付くリソースデータの総数を取得する
     *
     * @param pageId ページID
     * @return リソースデータの総数
     */
    Long countByPageId(Long pageId);

}
