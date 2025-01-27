package com.magiarium.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
@Table(name = "item_master", uniqueConstraints = {@UniqueConstraint(columnNames = {"item_type", "title"})})
public class ItemMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "is_active", nullable = false)
    Boolean isActive;

    @Column(name = "item_type", nullable = false)
    @Enumerated(EnumType.STRING)
    ItemTypeEnum itemType;

    @Column(name = "title", length = 45, nullable = false)
    String title;

    @Column(name = "description")
    String description;

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

    @OneToMany(mappedBy = "item")
    private List<ItemGroup> groupItems = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<ItemContent> itemContents = new ArrayList<>();

    @OneToMany(mappedBy = "content")
    private List<Thread> threads = new ArrayList<>();
}
