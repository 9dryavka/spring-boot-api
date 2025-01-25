package com.magiarium.service.illust;

import com.magiarium.domain.data.PageTypeEnum;
import com.magiarium.domain.entity.PageDataMaster;
import com.magiarium.domain.request.SearchIllustRequest;
import com.magiarium.domain.response.SearchIllustResponse;
import com.magiarium.repository.PageDataMasterRepository;
import com.magiarium.repository.ResourceDataMasterRepository;
import com.magiarium.domain.entity.ResourceDataMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchIllustService {

    @Autowired
    private PageDataMasterRepository pageDataMasterRepository;
    @Autowired
    private ResourceDataMasterRepository resourceDataMasterRepository;

    public SearchIllustResponse search(SearchIllustRequest request) {


        // まず最初に、イラストページ用のマスターデータを取得する
        // ※イラストページにページャーはない想定
        PageDataMaster illustPage = pageDataMasterRepository.findFirstByPageType(PageTypeEnum.ILLUST);

        // イラストページが見つからない場合、例外を投げる
        if (illustPage == null) {
            throw new RuntimeException("Illust page not found");
        }

        // ページIDからリソース総数を検索する
        Long total = resourceDataMasterRepository.countByPageId(illustPage.getId());
        if (total == 0) {
            // リソースが存在しない場合、移行の処理をスキップする
            return SearchIllustResponse.builder()
                    .build();
        }

        // ページIDからリソース情報リストを取得する
        List<ResourceDataMaster> resourceDataList =
                resourceDataMasterRepository.findResourceIdByPageId(illustPage.getId(), request.getLimit(), request.getOffset());

        // データをレスポンス用に加工する
        return SearchIllustResponse.builder()
                .total(total)
                .imageUrlInfos(resourceDataList.stream()
                        .map(resourceData -> SearchIllustResponse.ImageUrlInfo.builder()
                                .resourceUrl(resourceData.getResourceUrl())
                                .build())
                        .toList())
                .build();
    }

}
