package com.magiarium.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.magiarium.domain.data.PageTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@Table(
        name = "category_data_master",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"page_type", "label"})}
)
public class CategoryDataMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "page_type", nullable = false)
    @Enumerated(EnumType.STRING)
    PageTypeEnum pageType;

    @Column(name = "label", length = 45, nullable = false)
    String label;

    @Column(name = "description")
    String description;
}