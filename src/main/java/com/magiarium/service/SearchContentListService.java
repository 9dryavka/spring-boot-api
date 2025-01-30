package com.magiarium.service;

import com.magiarium.domain.dto.thumnail_item.ItemContentInfo;
import com.magiarium.domain.dto.thumnail_item.ItemContentResourceInfo;
import com.magiarium.domain.dto.thumnail_item.ItemInfo;
import com.magiarium.domain.enums.ContentTypeEnum;
import com.magiarium.domain.enums.ItemTypeEnum;
import com.magiarium.domain.dto.content_master.ContentMasterWithItemId;
import com.magiarium.domain.dto.item_master.ItemMasterWithCategoryAndView;
import com.magiarium.domain.dto.item_tag_master.ItemTagMasterWithItemId;
import com.magiarium.domain.dto.resource_master.ResourceMasterWithContentId;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        Map<Long, List<ContentMasterWithItemId>> contentMasterMap = contentMasterRepository.findByContentTypeAndItemIdInWithItemId(
                ContentTypeEnum.THUMBNAIL,
                itemIdList
        ).stream().collect(Collectors.groupingBy(ContentMasterWithItemId::getItemId));

        // アイテムIDをもとに、タグ一覧を取得
        Map<Long, List<ItemTagMasterWithItemId>> itemTagMasterMap = tagMasterRepository.findByItemIdIn(itemIdList)
                .stream().collect(Collectors.groupingBy(ItemTagMasterWithItemId::getItemId));

        // コンテンツIDをもとに、リソース一覧を取得
        Map<Long, List<ResourceMasterWithContentId>> resourceMasterMap = resourceMasterRepository.findByContentIdIn(
                contentMasterMap.keySet().stream().toList()
        ).stream().collect(Collectors.groupingBy(ResourceMasterWithContentId::getContentId));

        // アイテム単位に各データをまとめて、レスポンス情報を作成する
        List<ItemInfo> itemContentInfoList = new ArrayList<>();
        for (ItemMasterWithCategoryAndView baseData : responseContentList) {
            Long itemId = baseData.getId();

            List<ItemTagMasterWithItemId> itemTagInfoList = itemTagMasterMap.get(itemId);
            ContentMasterWithItemId itemContentInfo = contentMasterMap.get(itemId).get(0);
            List<ResourceMasterWithContentId> itemResourceInfoList = resourceMasterMap.get(itemContentInfo.getContentId());

            ItemContentResourceInfo resourceInfo =
                    ItemContentResourceInfo.builder()
                            .resourceId(itemResourceInfoList.get(0).getResourceId())
                            .resourceLabel(itemResourceInfoList.get(0).getResourceLabel())
                            .resourceType(itemResourceInfoList.get(0).getResourceType())
                            .resourceUrl(itemResourceInfoList.get(0).getResourceUrl())
                            .build();

            ItemContentInfo tmpContentInfo =
                    ItemContentInfo.builder()
                            .contentId(itemContentInfo.getContentId())
                            .contentType(itemContentInfo.getContentType())
                            .contentLabel(itemContentInfo.getLabel())
                            .contentDescription(itemContentInfo.getDescription())
                            .contentJson(itemContentInfo.getContentJson())
                            .contentCreatedAt(itemContentInfo.getCreatedAt())
                            .contentUpdatedAt(itemContentInfo.getUpdatedAt())
                            .resources(resourceInfo)
                            .build();

            ItemInfo tmpItemContentInfo =
                    ItemInfo.builder()
                            .itemId(itemId)
                            .itemType(itemType)
                            .itemTitle(baseData.getTitle())
                            .itemDescription(baseData.getDescription())
                            .itemCreatedAt(baseData.getCreatedAt())
                            .itemUpdatedAt(baseData.getUpdatedAt())
                            .itemGroupName(baseData.getGroupName())
                            .itemTags(itemTagInfoList.stream().map(ItemTagMasterWithItemId::getLabel).toList())
                            .contents(List.of(tmpContentInfo))
                            .build();

            itemContentInfoList.add(tmpItemContentInfo);

        }

        SearchThumbnailContentListResponse response = new SearchThumbnailContentListResponse();
        response.setTotal(total);
        response.setItemList(itemContentInfoList);

        return response;

    }

}
