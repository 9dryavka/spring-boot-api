package com.magiarium.repository.resource_master;

import com.magiarium.domain.entity.ResourceMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceMasterRepository extends JpaRepository<ResourceMaster, Long>, ResourceMasterRepositoryCustom {


}
