package com.company.movieticket.dao;

import com.company.movieticket.dto.ListScreeningRequest;
import com.company.movieticket.entity.ScreeningEntity;
import com.company.movieticket.repository.ScreeningRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class ScreeningDao extends Dao<ScreeningEntity, Integer, ScreeningRepository> {
    public ScreeningDao(EntityManager em, ScreeningRepository repository) {
        super(em, repository, ScreeningEntity.class);
    }

    public Page<ScreeningEntity> listScreening(ListScreeningRequest request) {
        // sql: SELECT e FROM ScreeningEntity e WHERE (e.id = :id) AND (e.cinema.id = :cinemaId) AND
        // (e.movie.id = :movieId) AND (:startTimeIsBefore <= e.startTime) AND (e.startTime <=
        // :startTimeIsAfter) AND (e.startTime = :startTime)
        Map<String, String> sortingOptions =
                Map.of(
                        "cinemaId",
                        "e.cinema.id",
                        "movieId",
                        "e.movie.id",
                        "startTime",
                        "e.startTime",
                        "id",
                        "e.id");
        Set<String> sortingCanStartWith = Set.of("cinemaId", "movieId", "startTime", "id");
        validateSort(request.getSort(), sortingOptions, sortingCanStartWith);

        SqlBuilder builder = new SqlBuilder();

        if (request.getId() != null) {
            builder.addWhereCondition("e.id = :id");
        }
        if (request.getCinemaId() != null) {
            builder.addWhereCondition("e.cinema.id = :cinemaId");
        }
        if (request.getMovieId() != null) {
            builder.addWhereCondition("e.movie.id = :movieId");
        }
        if (request.getStartTimeIsBefore() != null) {
            builder.addWhereCondition(":startTimeIsBefore <= e.startTime");
        }
        if (request.getStartTimeIsAfter() != null) {
            builder.addWhereCondition("e.startTime <= :startTimeIsAfter");
        }
        if (request.getStartTime() != null) {
            builder.addWhereCondition("e.startTime = :startTime");
        }

        String countStatement = builder.getQuery("SELECT COUNT(e) FROM ScreeningEntity e");
        String selectEntitiesStatement =
                builder.getQuery(
                        "SELECT e FROM ScreeningEntity e", request.getSort(), sortingOptions);
        TypedQuery<Long> countQuery = em.createQuery(countStatement, Long.class);
        listScreeningBindParameters(countQuery, request);
        long count = countQuery.getSingleResult();
        int firstResult = request.getPageIndex() * request.getPageSize();
        TypedQuery<ScreeningEntity> selectEntitiesQuery =
                em.createQuery(selectEntitiesStatement, ScreeningEntity.class);
        selectEntitiesQuery.setFirstResult(firstResult);
        selectEntitiesQuery.setMaxResults(request.getPageSize());
        listScreeningBindParameters(selectEntitiesQuery, request);
        List<ScreeningEntity> names = selectEntitiesQuery.getResultList();
        return new PageImpl(
                names, PageRequest.of(request.getPageIndex(), request.getPageSize()), count);
    }

    private void listScreeningBindParameters(TypedQuery query, ListScreeningRequest request) {
        if (request.getId() != null) {
            query.setParameter("id", request.getId());
        }
        if (request.getCinemaId() != null) {
            query.setParameter("cinemaId", request.getCinemaId());
        }
        if (request.getMovieId() != null) {
            query.setParameter("movieId", request.getMovieId());
        }
        if (request.getStartTimeIsBefore() != null) {
            query.setParameter("startTimeIsBefore", request.getStartTimeIsBefore());
        }
        if (request.getStartTimeIsAfter() != null) {
            query.setParameter("startTimeIsAfter", request.getStartTimeIsAfter());
        }
        if (request.getStartTime() != null) {
            query.setParameter("startTime", request.getStartTime());
        }
    }
}
