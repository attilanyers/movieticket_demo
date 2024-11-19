package com.company.movieticket.dao;

import com.company.movieticket.dto.ListMovieRequest;
import com.company.movieticket.entity.MovieEntity;
import com.company.movieticket.repository.MovieRepository;
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
public class MovieDao extends Dao<MovieEntity, Integer, MovieRepository> {
    public MovieDao(EntityManager em, MovieRepository repository) {
        super(em, repository, MovieEntity.class);
    }

    public Page<MovieEntity> listMovie(ListMovieRequest request) {
        // sql: SELECT e FROM MovieEntity e WHERE (e.id = :id) AND (e.title ILIKE CONCAT('%',
        // :title, '%')) AND (e.director ILIKE CONCAT('%', :director, '%')) AND (e.genre ILIKE
        // CONCAT('%', :genre, '%')) AND (:durationGreaterThanOrEqualTo <= e.duration) AND
        // (e.duration <= :durationLessThanOrEqualTo) AND (e.duration = :duration) AND
        // (:lengthGreaterThanOrEqualTo <= e.length) AND (e.length <= :lengthLessThanOrEqualTo) AND
        // (e.length = :length)
        Map<String, String> sortingOptions =
                Map.of(
                        "duration",
                        "e.duration",
                        "director",
                        "e.director",
                        "genre",
                        "e.genre",
                        "length",
                        "e.length",
                        "id",
                        "e.id",
                        "title",
                        "e.title");
        Set<String> sortingCanStartWith =
                Set.of("duration", "director", "genre", "length", "id", "title");
        validateSort(request.getSort(), sortingOptions, sortingCanStartWith);

        SqlBuilder builder = new SqlBuilder();

        if (request.getId() != null) {
            builder.addWhereCondition("e.id = :id");
        }
        if (request.getTitle() != null) {
            builder.addWhereCondition("e.title ILIKE CONCAT('%', :title, '%')");
        }
        if (request.getDirector() != null) {
            builder.addWhereCondition("e.director ILIKE CONCAT('%', :director, '%')");
        }
        if (request.getGenre() != null) {
            builder.addWhereCondition("e.genre ILIKE CONCAT('%', :genre, '%')");
        }
        if (request.getDurationGreaterThanOrEqualTo() != null) {
            builder.addWhereCondition(":durationGreaterThanOrEqualTo <= e.duration");
        }
        if (request.getDurationLessThanOrEqualTo() != null) {
            builder.addWhereCondition("e.duration <= :durationLessThanOrEqualTo");
        }
        if (request.getDuration() != null) {
            builder.addWhereCondition("e.duration = :duration");
        }
        if (request.getLengthGreaterThanOrEqualTo() != null) {
            builder.addWhereCondition(":lengthGreaterThanOrEqualTo <= e.length");
        }
        if (request.getLengthLessThanOrEqualTo() != null) {
            builder.addWhereCondition("e.length <= :lengthLessThanOrEqualTo");
        }
        if (request.getLength() != null) {
            builder.addWhereCondition("e.length = :length");
        }

        String countStatement = builder.getQuery("SELECT COUNT(e) FROM MovieEntity e");
        String selectEntitiesStatement =
                builder.getQuery("SELECT e FROM MovieEntity e", request.getSort(), sortingOptions);
        TypedQuery<Long> countQuery = em.createQuery(countStatement, Long.class);
        listMovieBindParameters(countQuery, request);
        long count = countQuery.getSingleResult();
        int firstResult = request.getPageIndex() * request.getPageSize();
        TypedQuery<MovieEntity> selectEntitiesQuery =
                em.createQuery(selectEntitiesStatement, MovieEntity.class);
        selectEntitiesQuery.setFirstResult(firstResult);
        selectEntitiesQuery.setMaxResults(request.getPageSize());
        listMovieBindParameters(selectEntitiesQuery, request);
        List<MovieEntity> names = selectEntitiesQuery.getResultList();
        return new PageImpl(
                names, PageRequest.of(request.getPageIndex(), request.getPageSize()), count);
    }

    private void listMovieBindParameters(TypedQuery query, ListMovieRequest request) {
        if (request.getId() != null) {
            query.setParameter("id", request.getId());
        }
        if (request.getTitle() != null) {
            query.setParameter("title", request.getTitle());
        }
        if (request.getDirector() != null) {
            query.setParameter("director", request.getDirector());
        }
        if (request.getGenre() != null) {
            query.setParameter("genre", request.getGenre());
        }
        if (request.getDurationGreaterThanOrEqualTo() != null) {
            query.setParameter(
                    "durationGreaterThanOrEqualTo", request.getDurationGreaterThanOrEqualTo());
        }
        if (request.getDurationLessThanOrEqualTo() != null) {
            query.setParameter("durationLessThanOrEqualTo", request.getDurationLessThanOrEqualTo());
        }
        if (request.getDuration() != null) {
            query.setParameter("duration", request.getDuration());
        }
        if (request.getLengthGreaterThanOrEqualTo() != null) {
            query.setParameter(
                    "lengthGreaterThanOrEqualTo", request.getLengthGreaterThanOrEqualTo());
        }
        if (request.getLengthLessThanOrEqualTo() != null) {
            query.setParameter("lengthLessThanOrEqualTo", request.getLengthLessThanOrEqualTo());
        }
        if (request.getLength() != null) {
            query.setParameter("length", request.getLength());
        }
    }
}
