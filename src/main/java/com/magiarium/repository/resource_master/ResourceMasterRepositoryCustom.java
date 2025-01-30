package com.magiarium.repository.resource_master;

import com.magiarium.domain.dto.resource_master.ResourceMasterWithContentId;

import java.util.List;

public interface ResourceMasterRepositoryCustom {

    /**
     * コンテンツIDに紐付くリソース情報リストを取得する
     *
     * @param contentIdList コンテンツIDリスト
     * @return リソース情報リスト
     */
    List<ResourceMasterWithContentId> findByContentIdIn(List<Long> contentIdList);

}
