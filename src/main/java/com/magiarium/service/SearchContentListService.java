package com.magiarium.service;

import com.magiarium.domain.data.ContentTypeEnum;
import com.magiarium.domain.data.ItemTypeEnum;
import com.magiarium.domain.entity.ContentMaster;
import com.magiarium.domain.request.SearchContentListRequest;
import com.magiarium.domain.response.SearchContentListResponse;
import com.magiarium.repository.content_master.ContentMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchContentListService {

    @Autowired
    private ContentMasterRepository contentMasterRepository;


    public SearchContentListResponse search(ItemTypeEnum itemType, SearchContentListRequest request) {

        // アイテムマスタ検索
        // 条件に一致するコンテンツ総件数を取得する
        Long total = contentMasterRepository.countByCategoryAndTagAndItemTypeAndContentType(
                itemType,
                request.getCategory(),
                request.getTagList(),
                request.getSearchQuery(),
                ContentTypeEnum.THUMBNAIL
        );

        // 条件に一致するコンテンツIDリストを取得する
        Pageable pageable = PageRequest.of(request.getOffset(), request.getLimit());
        List<ContentMaster> responseContentList = contentMasterRepository.findContentIdByCategoryAndTagAndItemTypeAndContentType(
                itemType,
                request.getCategory(),
                request.getTagList(),
                request.getSearchQuery(),
                ContentTypeEnum.THUMBNAIL,
                pageable
        );

        // コンテンツ一覧
        // カテゴリ一覧
        // タグ一覧
        // リソース一覧

        // アイテムIDごとにレスポンス情報を作成する
        for (ContentMaster targetContent : responseContentList) {
            // リソース取得を取得


        }

        return null;

    }

}
