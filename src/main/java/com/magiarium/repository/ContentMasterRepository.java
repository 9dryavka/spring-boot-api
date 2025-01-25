package com.magiarium.repository;

import com.magiarium.domain.data.ContentTypeEnum;
import com.magiarium.domain.entity.ContentMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentMasterRepository extends JpaRepository<ContentMaster, Long> {

    ContentMaster findFirstByContentType(ContentTypeEnum contentType);

    Page<ContentMaster> findByContentType(ContentTypeEnum contentType, Pageable pageable);

}
