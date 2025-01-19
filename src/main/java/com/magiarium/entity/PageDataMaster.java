package com.magiarium.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.magiarium.data.utils.PageTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@Table(name = "page_data_master")
public class PageDataMaster {

    @Id
    @Column(name = "id")
    Long id;

    @Column(name = "page_type")
    @Enumerated(EnumType.STRING)
    PageTypeEnum PageTypeEnum;

    @Column(name = "title", length = 45)
    String title;

    @Column(name = "description", length = 256)
    String description;

    @Column(name = "category_id")
    Long categoryId;

    @Column(name = "author_id")
    Long authorId;

    @Column(name = "content_id")
    Long contentId;

    @Column(name = "thread_id")
    Long threadId;

    @Column(name = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createdAt;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date updatedAt;
}
