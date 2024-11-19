package com.company.movieticket.dao;

import com.company.movieticket.dto.ListReservationRequest;
import com.company.movieticket.entity.ReservationEntity;
import com.company.movieticket.repository.ReservationRepository;
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
public class ReservationDao extends Dao<ReservationEntity, Integer, ReservationRepository> {
    public ReservationDao(EntityManager em, ReservationRepository repository) {
        super(em, repository, ReservationEntity.class);
    }

    public Page<ReservationEntity> listReservation(ListReservationRequest request) {
        // sql: SELECT e FROM ReservationEntity e WHERE (e.id = :id) AND (e.screening.id =
        // :screeningId) AND (e.user.id = :userId) AND (e.seatNumber ILIKE CONCAT('%', :seatNumber,
        // '%'))
        Map<String, String> sortingOptions =
                Map.of(
                        "screeningId",
                        "e.screening.id",
                        "id",
                        "e.id",
                        "userId",
                        "e.user.id",
                        "seatNumber",
                        "e.seatNumber");
        Set<String> sortingCanStartWith = Set.of("screeningId", "id", "userId", "seatNumber");
        validateSort(request.getSort(), sortingOptions, sortingCanStartWith);

        SqlBuilder builder = new SqlBuilder();

        if (request.getId() != null) {
            builder.addWhereCondition("e.id = :id");
        }
        if (request.getScreeningId() != null) {
            builder.addWhereCondition("e.screening.id = :screeningId");
        }
        if (request.getUserId() != null) {
            builder.addWhereCondition("e.user.id = :userId");
        }
        if (request.getSeatNumber() != null) {
            builder.addWhereCondition("e.seatNumber ILIKE CONCAT('%', :seatNumber, '%')");
        }

        String countStatement = builder.getQuery("SELECT COUNT(e) FROM ReservationEntity e");
        String selectEntitiesStatement =
                builder.getQuery(
                        "SELECT e FROM ReservationEntity e", request.getSort(), sortingOptions);
        TypedQuery<Long> countQuery = em.createQuery(countStatement, Long.class);
        listReservationBindParameters(countQuery, request);
        long count = countQuery.getSingleResult();
        int firstResult = request.getPageIndex() * request.getPageSize();
        TypedQuery<ReservationEntity> selectEntitiesQuery =
                em.createQuery(selectEntitiesStatement, ReservationEntity.class);
        selectEntitiesQuery.setFirstResult(firstResult);
        selectEntitiesQuery.setMaxResults(request.getPageSize());
        listReservationBindParameters(selectEntitiesQuery, request);
        List<ReservationEntity> names = selectEntitiesQuery.getResultList();
        return new PageImpl(
                names, PageRequest.of(request.getPageIndex(), request.getPageSize()), count);
    }

    private void listReservationBindParameters(TypedQuery query, ListReservationRequest request) {
        if (request.getId() != null) {
            query.setParameter("id", request.getId());
        }
        if (request.getScreeningId() != null) {
            query.setParameter("screeningId", request.getScreeningId());
        }
        if (request.getUserId() != null) {
            query.setParameter("userId", request.getUserId());
        }
        if (request.getSeatNumber() != null) {
            query.setParameter("seatNumber", request.getSeatNumber());
        }
    }
}
