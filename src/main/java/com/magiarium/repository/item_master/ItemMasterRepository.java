package com.magiarium.repository.item_master;

import com.magiarium.domain.entity.ItemMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemMasterRepository extends JpaRepository<ItemMaster, Long>, ItemMasterRepositoryCustom {
}
