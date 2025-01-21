package com.magiarium.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
@Table(name = "page_tag", uniqueConstraints = @UniqueConstraint(columnNames = {"page_id", "tag_id"}))
public class PageTag {

    @Id
    @Column(name = "index")
    Long index;

    @Column(name = "page_id", nullable = false)
    Long pageId;

    @Column(name = "tag_id", nullable = false)
    Long tagId;

    @Column(name = "created_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date updatedAt;
}
