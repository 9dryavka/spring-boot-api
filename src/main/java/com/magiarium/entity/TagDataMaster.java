package com.magiarium.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.magiarium.data.utils.PageTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@Table(name = "tag_data_master", uniqueConstraints = {@UniqueConstraint(columnNames = {"page_type", "label"})})
public class TagDataMaster {

    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "page_type", nullable = false)
    @Enumerated(EnumType.STRING)
    PageTypeEnum PageTypeEnum;

    @Column(name = "label", length = 45, nullable = false)
    String label;

    @Column(name = "description", length = 256)
    String description;
}
