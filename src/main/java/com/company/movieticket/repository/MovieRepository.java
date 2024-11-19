package com.company.movieticket.repository;

import com.company.movieticket.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository
        extends JpaRepository<MovieEntity, Integer>, JpaSpecificationExecutor<MovieEntity> {}
