package com.magiarium.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@Table(name = "page_attach_resource")
public class PageAttachResource {

    @Id
    @Column(name = "index")
    Long index;

    @Column(name = "page_id")
    Long pageId;

    @Column(name = "resource_type")
    String resourceType;

    @Column(name = "resource_id")
    Long resourceId;

}
