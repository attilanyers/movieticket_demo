package com.company.movieticket.dao;

import com.company.movieticket.dto.ListCinemaRequest;
import com.company.movieticket.entity.CinemaEntity;
import com.company.movieticket.repository.CinemaRepository;
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
public class CinemaDao extends Dao<CinemaEntity, Integer, CinemaRepository> {
    public CinemaDao(EntityManager em, CinemaRepository repository) {
        super(em, repository, CinemaEntity.class);
    }

    public Page<CinemaEntity> listCinema(ListCinemaRequest request) {
        // sql: SELECT e FROM CinemaEntity e WHERE (e.id = :id) AND (e.name ILIKE CONCAT('%', :name,
        // '%')) AND (e.location ILIKE CONCAT('%', :location, '%'))
        Map<String, String> sortingOptions =
                Map.of("name", "e.name", "location", "e.location", "id", "e.id");
        Set<String> sortingCanStartWith = Set.of("name", "location", "id");
        validateSort(request.getSort(), sortingOptions, sortingCanStartWith);

        SqlBuilder builder = new SqlBuilder();

        if (request.getId() != null) {
            builder.addWhereCondition("e.id = :id");
        }
        if (request.getName() != null) {
            builder.addWhereCondition("e.name ILIKE CONCAT('%', :name, '%')");
        }
        if (request.getLocation() != null) {
            builder.addWhereCondition("e.location ILIKE CONCAT('%', :location, '%')");
        }

        String countStatement = builder.getQuery("SELECT COUNT(e) FROM CinemaEntity e");
        String selectEntitiesStatement =
                builder.getQuery("SELECT e FROM CinemaEntity e", request.getSort(), sortingOptions);
        TypedQuery<Long> countQuery = em.createQuery(countStatement, Long.class);
        listCinemaBindParameters(countQuery, request);
        long count = countQuery.getSingleResult();
        int firstResult = request.getPageIndex() * request.getPageSize();
        TypedQuery<CinemaEntity> selectEntitiesQuery =
                em.createQuery(selectEntitiesStatement, CinemaEntity.class);
        selectEntitiesQuery.setFirstResult(firstResult);
        selectEntitiesQuery.setMaxResults(request.getPageSize());
        listCinemaBindParameters(selectEntitiesQuery, request);
        List<CinemaEntity> names = selectEntitiesQuery.getResultList();
        return new PageImpl(
                names, PageRequest.of(request.getPageIndex(), request.getPageSize()), count);
    }

    private void listCinemaBindParameters(TypedQuery query, ListCinemaRequest request) {
        if (request.getId() != null) {
            query.setParameter("id", request.getId());
        }
        if (request.getName() != null) {
            query.setParameter("name", request.getName());
        }
        if (request.getLocation() != null) {
            query.setParameter("location", request.getLocation());
        }
    }
}
