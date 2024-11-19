package com.company.movieticket.entity;

import com.company.movieticket.entity.BaseEntity;
import com.company.movieticket.entity.ScreeningEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "CINEMA")
public class CinemaEntity extends BaseEntity {
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue
    private Integer id;

    @Column private String name;
    @Column private String location;

    @OneToMany(mappedBy = "cinema")
    private List<ScreeningEntity> screenings;
}
