package com.magiarium.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@Table(name = "thread")
public class Thread {

    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "page_id", nullable = false)
    Long pageId;

    @Column(name = "title", length = 45)
    String title;

    @Column(name = "description", length = 256)
    String description;

    @Column(name = "created_by", length = 45)
    String createdBy;

    @Column(name = "created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date updatedAt;

}
