package com.magiarium.repository.item_master;

import com.magiarium.domain.data.ItemTypeEnum;
import com.magiarium.domain.entity.ItemMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMasterRepository extends JpaRepository<ItemMaster, Long>, ItemMasterRepositoryCustom {

    List<ItemMaster> findByItemTypeAndTitleLike(ItemTypeEnum itemType, String title);
}
