package com.magiarium.service.illust;

import com.magiarium.domain.data.ContentTypeEnum;
import com.magiarium.domain.entity.ContentMaster;
import com.magiarium.domain.request.illust.SearchIllustListRequest;
import com.magiarium.domain.response.illust.SearchIllustListResponse;
import com.magiarium.repository.ContentMasterRepository;
import com.magiarium.repository.ResourceDataMasterRepository;
import com.magiarium.domain.entity.ResourceMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchIllustListService {

    @Autowired
    private ContentMasterRepository contentMasterRepository;
    @Autowired
    private ResourceDataMasterRepository resourceDataMasterRepository;

    public SearchIllustListResponse search(SearchIllustListRequest request) {


        // まず最初に、イラストページ用のマスターデータを取得する
        // ※イラストページにページャーはない想定
        ContentMaster illustPage = contentMasterRepository.findFirstByContentType(ContentTypeEnum.ILLUST);

        // コンテンツが見つからない場合、例外を投げる
        if (illustPage == null) {
            throw new RuntimeException("Illust page not found");
        }

        // コンテンツIDからリソース総数を検索する
        Long total = resourceDataMasterRepository.countByContentId(illustPage.getId());
        if (total == 0) {
            // リソースが存在しない場合、移行の処理をスキップする
            return SearchIllustListResponse.builder()
                    .build();
        }

        // コンテンツIDからリソース情報リストを取得する
        List<ResourceMaster> resourceDataList =
                resourceDataMasterRepository.findResourceIdByContentId(illustPage.getId(), request.getLimit(), request.getOffset());

        // データをレスポンス用に加工する
        return SearchIllustListResponse.builder()
                .total(total)
                .imageUrlInfos(resourceDataList.stream()
                        .map(resourceData -> SearchIllustListResponse.ImageUrlInfo.builder()
                                .resourceUrl(resourceData.getResourceUrl())
                                .build())
                        .toList())
                .build();
    }

}
