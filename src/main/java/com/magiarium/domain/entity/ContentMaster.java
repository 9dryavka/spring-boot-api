package com.magiarium.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.magiarium.domain.data.ContentTypeEnum;
import com.magiarium.domain.data.ItemTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@Table(name = "content_master")
public class ContentMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content_type", nullable = false)
    @Enumerated(EnumType.STRING)
    ContentTypeEnum contentType;

    @Column(name = "label", length = 45, nullable = false)
    String label;

    @Column(name = "description")
    String description;

    @Column(name = "content_json", nullable = false, columnDefinition = "JSON")
    String contentJson;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Timestamp createdAt;

    @Column(name = "updated_at", nullable = false, insertable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Timestamp updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = new Timestamp(new Date().getTime());
        updatedAt = new Timestamp(new Date().getTime());
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = new Timestamp(new Date().getTime());
    }

    @OneToMany(mappedBy = "content")
    private List<ContentResource> contentResources = new ArrayList<>();

}
