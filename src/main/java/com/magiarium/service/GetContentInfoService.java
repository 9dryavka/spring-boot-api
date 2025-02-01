package com.magiarium.service;

import com.magiarium.domain.dto.content_master.ChildContent;
import com.magiarium.domain.dto.content_master.MainContentItem;
import com.magiarium.domain.dto.item_tag_master.ItemTagMasterWithItemId;
import com.magiarium.domain.dto.resource_master.ResourceMasterWithContentId;
import com.magiarium.domain.dto.thumnail_item.ItemContentInfo;
import com.magiarium.domain.dto.thumnail_item.ItemContentResourceInfo;
import com.magiarium.domain.response.GetMainContentResponse;
import com.magiarium.exception.NotFoundException;
import com.magiarium.repository.content_master.ContentMasterRepository;
import com.magiarium.repository.item_master.ItemMasterRepository;
import com.magiarium.repository.item_tag_master.TagMasterRepository;
import com.magiarium.repository.resource_master.ResourceMasterRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GetContentInfoService {

    @Autowired
    private ItemMasterRepository itemMasterRepository;
    @Autowired
    private ContentMasterRepository contentMasterRepository;
    @Autowired
    private TagMasterRepository tagMasterRepository;
    @Autowired
    private ResourceMasterRepository resourceMasterRepository;

    public GetMainContentResponse get(Long itemId) {

        // アイテムIDをもとに、メインコンテンツ情報を取得する
        MainContentItem mainContentInfo;
        try {
            mainContentInfo = contentMasterRepository.getMainContentByItemId(itemId);
        } catch (Exception ignored) {
            throw new NotFoundException("メインコンテンツが見つかりませんでした");
        }
        if (mainContentInfo == null) {
            throw new NotFoundException("メインコンテンツが見つかりませんでした");
        }

        // 以下、メインコンテンツ付帯情報の取得
        // アイテムIDをもとに、タグ一覧を取得
        List<ItemTagMasterWithItemId> itemTagList = tagMasterRepository.findByItemIdIn(List.of(itemId));

        // コンテンツIDをもとに、存在する場合は子コンテンツを取得
        List<ChildContent> childContentList = contentMasterRepository.getChildContentListByContentId(mainContentInfo.getContentId());
        List<Long> contentIdList = new ArrayList<>(childContentList.stream().map(ChildContent::getChildContentId).toList());
        contentIdList.add(mainContentInfo.getContentId());

        // コンテンツIDをもとに、リソース一覧を取得
        Map<Long, List<ResourceMasterWithContentId>> resourceMasterMap = resourceMasterRepository.findByContentIdIn(
                contentIdList
        ).stream().collect(Collectors.groupingBy(ResourceMasterWithContentId::getContentId));

        // レスポンスデータの作成
        List<ItemContentInfo> responseChildContentList = new ArrayList<>();
        for (ChildContent childContent : childContentList) {

            // リソースを取得
            List<ItemContentResourceInfo> tmpResourceList = new ArrayList<>();
            for (ResourceMasterWithContentId targetResource : resourceMasterMap.get(childContent.getChildContentId())) {
                tmpResourceList.add(ItemContentResourceInfo.builder()
                        .resourceId(targetResource.getResourceId())
                        .resourceLabel(targetResource.getResourceLabel())
                        .resourceType(targetResource.getResourceType())
                        .resourceUrl(targetResource.getResourceUrl())
                        .build());

            }

            ItemContentInfo tmpChildContent = ItemContentInfo.builder()
                    .contentId(childContent.getChildContentId())
                    .contentType(childContent.getContentType())
                    .contentLabel(childContent.getLabel())
                    .contentJson(childContent.getContentJson())
                    .resources(tmpResourceList)
                    .build();

            responseChildContentList.add(tmpChildContent);
        }

        // メインコンテンツのリソースを取得
        List<ItemContentResourceInfo> mainResourceList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(resourceMasterMap)) {
            for (ResourceMasterWithContentId targetMainResource : resourceMasterMap.get(mainContentInfo.getContentId())) {
                mainResourceList.add(ItemContentResourceInfo.builder()
                        .resourceId(targetMainResource.getResourceId())
                        .resourceLabel(targetMainResource.getResourceLabel())
                        .resourceType(targetMainResource.getResourceType())
                        .resourceUrl(targetMainResource.getResourceUrl())
                        .build());
            }
        }

        ItemContentInfo mainContent = ItemContentInfo.builder()
                .contentId(mainContentInfo.getContentId())
                .contentType(mainContentInfo.getContentType())
                .contentLabel(mainContentInfo.getContentLabel())
                .contentJson(mainContentInfo.getContentJson())
                .resources(mainResourceList)
                .childContents(responseChildContentList)
                .build();

        return GetMainContentResponse.builder()
                .itemId(itemId)
                .itemType(mainContentInfo.getItemType())
                .itemTitle(mainContentInfo.getItemTitle())
                .itemDescription(mainContentInfo.getItemDescription())
                .itemCreatedAt(mainContentInfo.getItemCreatedAt())
                .itemUpdatedAt(mainContentInfo.getItemUpdatedAt())
                .itemGroupName(mainContentInfo.getItemGroupName())
                .itemTags(itemTagList.stream().map(ItemTagMasterWithItemId::getLabel).toList())
                .content(mainContent).build();

    }
}
