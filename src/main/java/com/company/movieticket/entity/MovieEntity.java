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
@Table(name = "MOVIE")
public class MovieEntity extends BaseEntity {
    @Id
    @Column(updatable = false, nullable = false)
    @GeneratedValue
    private Integer id;

    @Column private String title;
    @Column private String director;
    @Column private String genre;
    @Column private Integer duration;
    @Column private Short length;

    @OneToMany(mappedBy = "movie")
    private List<ScreeningEntity> screenings;
}
