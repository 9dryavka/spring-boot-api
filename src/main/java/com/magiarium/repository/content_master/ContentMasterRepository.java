package com.magiarium.repository.content_master;

import com.magiarium.domain.entity.ItemMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentMasterRepository extends JpaRepository<ItemMaster, Long>, ContentMasterRepositoryCustom {

}
