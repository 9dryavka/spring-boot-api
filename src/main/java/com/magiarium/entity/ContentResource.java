package com.magiarium.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;

@Entity
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@Table(name = "content_resource", uniqueConstraints = @UniqueConstraint(columnNames = {"content_id", "resource_id"}))
public class ContentResource {

    @Id
    @Column(name = "index")
    Long index;

    @Column(name = "content_id", nullable = false)
    Long contentId;

    @Column(name = "resource_id", nullable = false)
    Long resourceId;
}
