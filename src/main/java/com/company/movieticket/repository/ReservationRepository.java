package com.company.movieticket.repository;

import com.company.movieticket.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository
        extends JpaRepository<ReservationEntity, Integer>,
                JpaSpecificationExecutor<ReservationEntity> {}
