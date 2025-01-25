package com.magiarium.domain.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@Table(name = "thread_comment", uniqueConstraints = @UniqueConstraint(columnNames = {"thread_id", "comment_id"}))
public class ThreadComment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "thread_id", nullable = false)
    Long threadId;

    @Column(name = "comment_id", nullable = false)
    Long commentId;

    @Column(name = "comment", length = 1023, nullable = false)
    String comment;

    @Column(name = "created_by", length = 45)
    String createdBy;

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

}
