package com.company.movieticket.entity;

import com.company.movieticket.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Setter
@Getter
public abstract class BaseEntity {
    @Column @CreatedDate private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @CreatedBy
    private UserEntity createdBy;

    @Column @LastModifiedDate private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @LastModifiedBy
    private UserEntity updatedBy;
}
