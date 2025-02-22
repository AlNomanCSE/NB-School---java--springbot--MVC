package com.noman.nbSchool.model;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {


    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createAt;

    @CreatedBy
    @Column(updatable = false)
    private String createBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updateAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String updateBy;
}
