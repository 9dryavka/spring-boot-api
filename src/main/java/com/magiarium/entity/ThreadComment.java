package com.magiarium.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
@Table(name = "thread_comment", uniqueConstraints = @UniqueConstraint(columnNames = {"thread_id", "comment_id"}))
public class ThreadComment implements Serializable {

    @Id
    @Column(name = "index")
    Long index;

    @Column(name = "thread_id", nullable = false)
    Long threadId;

    @Column(name = "comment_id", nullable = false)
    Long commentId;

    @Column(name = "commented_by")
    String commentedBy;

    @Column(name = "comment", length = 256, nullable = false)
    String comment;

    @Column(name = "created_by")
    String createdBy;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date updatedAt;

}
