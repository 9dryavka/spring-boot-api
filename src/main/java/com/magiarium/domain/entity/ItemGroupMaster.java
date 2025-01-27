package com.magiarium.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.magiarium.domain.data.ItemGroupTypeEnum;
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
        name = "group_master",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"item_type", "label"})}
)
public class ItemGroupMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "group_type", nullable = false)
    @Enumerated(EnumType.STRING)
    ItemGroupTypeEnum groupType;

    @Column(name = "label", length = 45, nullable = false)
    String label;

    @Column(name = "description")
    String description;

    @OneToMany(mappedBy = "group")
    private List<ItemGroup> contentGroups = new ArrayList<>();
}