package com.company.movieticket.repository;

import com.company.movieticket.entity.ScreeningEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreeningRepository
        extends JpaRepository<ScreeningEntity, Integer>,
                JpaSpecificationExecutor<ScreeningEntity> {}
