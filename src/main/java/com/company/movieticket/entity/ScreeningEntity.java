package com.company.movieticket.entity;

import com.company.movieticket.entity.BaseEntity;
import com.company.movieticket.entity.CinemaEntity;
import com.company.movieticket.entity.MovieEntity;
import com.company.movieticket.entity.ReservationEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "SCREENING")
public class ScreeningEntity extends BaseEntity {
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue
    private Integer id;

    @Column private LocalDateTime startTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private CinemaEntity cinema;

    @ManyToOne(fetch = FetchType.LAZY)
    private MovieEntity movie;

    @OneToMany(mappedBy = "screening")
    private List<ReservationEntity> reservations;
}
