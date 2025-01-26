package com.magiarium.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.magiarium.domain.data.ContentTypeEnum;
import com.magiarium.domain.data.ItemTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@Table(
        name = "category_master",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"content_type", "label"})}
)
public class CategoryMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "item_type", nullable = false)
    @Enumerated(EnumType.STRING)
    ItemTypeEnum itemType;

    @Column(name = "label", length = 45, nullable = false)
    String label;

    @Column(name = "description")
    String description;

    @OneToMany(mappedBy = "category")
    private List<ItemCategory> contentCategories = new ArrayList<>();
}