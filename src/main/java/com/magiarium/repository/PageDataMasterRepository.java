package com.magiarium.repository;

import com.magiarium.domain.data.PageTypeEnum;
import com.magiarium.domain.entity.PageDataMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageDataMasterRepository extends JpaRepository<PageDataMaster, Long> {

    PageDataMaster findFirstByPageType(PageTypeEnum pageType);

    Page<PageDataMaster> findByPageType(PageTypeEnum pageType, Pageable pageable);

}
