package com.company.movieticket.repository;

import com.company.movieticket.entity.UserEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends JpaRepository<UserEntity, UUID>, JpaSpecificationExecutor<UserEntity> {
    @Query(value = "SELECT u FROM UserEntity u JOIN FETCH u.userAuthorities WHERE u.email = :email")
    Optional<UserEntity> findByEmailFetchAuthorities(String email);

    @Query(value = "SELECT u FROM UserEntity u JOIN FETCH u.userAuthorities WHERE u.id = :id")
    Optional<UserEntity> findByIdFetchAuthorities(UUID id);
}
