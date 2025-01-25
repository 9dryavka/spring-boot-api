package com.magiarium.repository;

import com.magiarium.domain.data.ResourceTypeEnum;
import com.magiarium.domain.entity.ResourceMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceDataMasterRepository extends JpaRepository<ResourceMaster, Long>, ResourceDataMasterRepositoryCustom {

    List<ResourceMaster> findByResourceType(ResourceTypeEnum resourceType);

}
