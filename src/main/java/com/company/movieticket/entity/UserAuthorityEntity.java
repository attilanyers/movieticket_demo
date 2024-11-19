package com.company.movieticket.entity;

import com.company.movieticket.entity.BaseEntity;
import com.company.movieticket.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Setter
@Getter
@Entity
@Table(name = "USER_AUTHORITY")
public class UserAuthorityEntity extends BaseEntity implements GrantedAuthority {
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue
    @NotNull
    private UUID id;

    @Column @NotBlank private String authority;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;
}
