package com.magiarium.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.magiarium.data.utils.ResourceTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@Table(name = "resource_data_master")
public class ResourceDataMaster {

    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "title", length = 45, nullable = false)
    String title;

    @Column(name = "description", length = 256)
    String description;

    @Column(name = "resource_type", nullable = false)
    @Enumerated(EnumType.STRING)
    ResourceTypeEnum resourceType;

    @Column(name = "resource_url", length = 256, nullable = false, unique = true)
    String resourceUrl;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createdAt;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date updatedAt;
}
