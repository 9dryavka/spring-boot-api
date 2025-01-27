package com.magiarium.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.magiarium.domain.data.ContentAttributeTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@Table(name = "content_attribute", uniqueConstraints = {@UniqueConstraint(columnNames = {"attribute_type", "attribute_id", "content_id"})})
public class ContentAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "attribute_type", nullable = false)
    @Enumerated(EnumType.STRING)
    ContentAttributeTypeEnum attributeType;

    @Column(name = "attribute_id")
    Integer attributeId;

    @ManyToOne
    @Column(name = "content_id", nullable = false)
    ContentMaster contentMaster;

}
