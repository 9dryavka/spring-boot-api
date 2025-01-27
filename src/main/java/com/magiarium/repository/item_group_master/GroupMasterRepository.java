package com.magiarium.repository.item_group_master;

import com.magiarium.domain.entity.ItemGroupMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMasterRepository extends JpaRepository<ItemGroupMaster, Long>, GroupMasterRepositoryCustom {

}
