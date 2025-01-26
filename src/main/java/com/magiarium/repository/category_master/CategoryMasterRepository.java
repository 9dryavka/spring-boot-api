package com.magiarium.repository.category_master;

import com.magiarium.domain.entity.CategoryMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryMasterRepository extends JpaRepository<CategoryMaster, Long> {

}
