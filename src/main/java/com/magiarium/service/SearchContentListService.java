package com.magiarium.service;

import com.magiarium.domain.data.ContentTypeEnum;
import com.magiarium.domain.data.ItemTypeEnum;
import com.magiarium.domain.dto.ContentMasterWithItemId;
import com.magiarium.domain.dto.ItemMasterWithCategoryAndView;
import com.magiarium.domain.dto.ItemTagMasterWithItemId;
import com.magiarium.domain.dto.ResourceMasterWithContentId;
import com.magiarium.domain.request.SearchThumbnailContentListRequest;
import com.magiarium.domain.response.SearchThumbnailContentListResponse;
import com.magiarium.repository.content_master.ContentMasterRepository;
import com.magiarium.repository.item_master.ItemMasterRepository;
import com.magiarium.repository.resource_master.ResourceMasterRepository;
import com.magiarium.repository.item_tag_master.TagMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchContentListService {

    @Autowired
    private ItemMasterRepository itemMasterRepository;
    @Autowired
    private ContentMasterRepository contentMasterRepository;
    @Autowired
    private TagMasterRepository tagMasterRepository;
    @Autowired
    private ResourceMasterRepository resourceMasterRepository;

    /**
     * サムネイルコンテンツ一覧を検索する
     *
     * @param itemType アイテム種別
     * @param request  検索リクエスト
     * @return レスポンス用のコンテンツ一覧
     */
    public SearchThumbnailContentListResponse search(ItemTypeEnum itemType, SearchThumbnailContentListRequest request) {

        // クライアント側の検索条件に基づいて、条件に合致するアイテムデータの総件数を取得する
        Long total = itemMasterRepository.countByClientSearch(
                itemType,
                request.getGroupType(),
                request.getGroupName(),
                request.getTagList(),
                request.getSearchQuery()
        );

        // 条件に合致するアイテムデータを実際に取得する
        // ※この際、アイテムデータに対して1対1の関係にあるコンテンツデータ(カテゴリ、レビュー数)はまとめて取得する
        Pageable pageable = PageRequest.of(request.getOffset(), request.getLimit());
        List<ItemMasterWithCategoryAndView> responseContentList = itemMasterRepository.findByClientSearch(
                itemType,
                request.getGroupType(),
                request.getGroupName(),
                request.getTagList(),
                request.getSearchQuery(),
                request.getOrderBy(),
                pageable
        );

        // DB検索用に、アイテムIDのリストを作成する
        List<Long> itemIdList = responseContentList.stream()
                .map(ItemMasterWithCategoryAndView::getId)
                .toList();

        // 以下、アイテムデータに対して1対Nの関係にあるテーブルからデータを取得する
        // アイテムIDとコンテンツタイプをもとに、コンテンツ一覧を取得する
        List<ContentMasterWithItemId> contentMasterWithContentId = contentMasterRepository.findByContentTypeAndItemIdInWithItemId(
                ContentTypeEnum.THUMBNAIL,
                itemIdList
        );

        // アイテムIDをもとに、タグ一覧を取得
        List<ItemTagMasterWithItemId> itemTagMasterWithItemId = tagMasterRepository.findByItemIdIn(itemIdList);

        // コンテンツIDをもとに、リソース一覧を取得
        List<ResourceMasterWithContentId> resourceMasterWithContentId = resourceMasterRepository.findByContentIdIn(
                contentMasterWithContentId.stream()
                        .map(ContentMasterWithItemId::getContentId)
                        .toList()
        );

        // アイテム単位に各データをまとめて、レスポンス情報を作成する
        // TODO ループで回してレスポンスを作成する

        SearchThumbnailContentListResponse response = new SearchThumbnailContentListResponse();
        response.setTotal(total);

        return response;

    }

}
