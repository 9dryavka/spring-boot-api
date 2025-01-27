package com.magiarium.repository.item_tag_master;

import com.magiarium.domain.entity.ItemTagMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagMasterRepository extends JpaRepository<ItemTagMaster, Long>, TagMasterRepositoryCustom {

}
