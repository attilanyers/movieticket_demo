package com.company.movieticket.repository;

import com.company.movieticket.entity.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository
        extends JpaRepository<CinemaEntity, Integer>, JpaSpecificationExecutor<CinemaEntity> {}
