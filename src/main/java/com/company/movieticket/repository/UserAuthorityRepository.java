package com.company.movieticket.repository;

import com.company.movieticket.entity.UserAuthorityEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthorityRepository
        extends JpaRepository<UserAuthorityEntity, UUID>,
                JpaSpecificationExecutor<UserAuthorityEntity> {}
