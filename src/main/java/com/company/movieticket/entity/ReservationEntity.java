package com.company.movieticket.entity;

import com.company.movieticket.entity.BaseEntity;
import com.company.movieticket.entity.ScreeningEntity;
import com.company.movieticket.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "RESERVATION")
public class ReservationEntity extends BaseEntity {
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue
    private Integer id;

    @Column private String seatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private ScreeningEntity screening;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;
}
