package com.magiarium.domain.dto;

import com.magiarium.domain.data.ContentTypeEnum;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

public class ResourceMasterWithContentId {

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
}
