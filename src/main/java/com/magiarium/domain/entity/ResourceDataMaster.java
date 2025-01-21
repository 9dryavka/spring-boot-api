package com.magiarium.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.magiarium.domain.data.ResourceTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date updatedAt;
}
