package com.magiarium.repository.tag_master;

import com.magiarium.domain.entity.TagMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagMasterRepository extends JpaRepository<TagMaster, Long> {
    
}
