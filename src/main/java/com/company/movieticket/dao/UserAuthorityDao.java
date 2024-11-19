package com.company.movieticket.dao;

import com.company.movieticket.dto.ListUserAuthorityRequest;
import com.company.movieticket.entity.UserAuthorityEntity;
import com.company.movieticket.repository.UserAuthorityRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthorityDao extends Dao<UserAuthorityEntity, UUID, UserAuthorityRepository> {
    public UserAuthorityDao(EntityManager em, UserAuthorityRepository repository) {
        super(em, repository, UserAuthorityEntity.class);
    }

    public Page<UserAuthorityEntity> listUserAuthority(ListUserAuthorityRequest request) {
        // sql: SELECT e FROM UserAuthorityEntity e WHERE (e.id = :id) AND (e.user.id = :userId) AND
        // (e.authority ILIKE CONCAT('%', :authority, '%'))
        Map<String, String> sortingOptions =
                Map.of("authority", "e.authority", "id", "e.id", "userId", "e.user.id");
        Set<String> sortingCanStartWith = Set.of("authority", "id", "userId");
        validateSort(request.getSort(), sortingOptions, sortingCanStartWith);

        SqlBuilder builder = new SqlBuilder();

        if (request.getId() != null) {
            builder.addWhereCondition("e.id = :id");
        }
        if (request.getUserId() != null) {
            builder.addWhereCondition("e.user.id = :userId");
        }
        if (request.getAuthority() != null) {
            builder.addWhereCondition("e.authority ILIKE CONCAT('%', :authority, '%')");
        }

        String countStatement = builder.getQuery("SELECT COUNT(e) FROM UserAuthorityEntity e");
        String selectEntitiesStatement =
                builder.getQuery(
                        "SELECT e FROM UserAuthorityEntity e", request.getSort(), sortingOptions);
        TypedQuery<Long> countQuery = em.createQuery(countStatement, Long.class);
        listUserAuthorityBindParameters(countQuery, request);
        long count = countQuery.getSingleResult();
        int firstResult = request.getPageIndex() * request.getPageSize();
        TypedQuery<UserAuthorityEntity> selectEntitiesQuery =
                em.createQuery(selectEntitiesStatement, UserAuthorityEntity.class);
        selectEntitiesQuery.setFirstResult(firstResult);
        selectEntitiesQuery.setMaxResults(request.getPageSize());
        listUserAuthorityBindParameters(selectEntitiesQuery, request);
        List<UserAuthorityEntity> names = selectEntitiesQuery.getResultList();
        return new PageImpl(
                names, PageRequest.of(request.getPageIndex(), request.getPageSize()), count);
    }

    private void listUserAuthorityBindParameters(
            TypedQuery query, ListUserAuthorityRequest request) {
        if (request.getId() != null) {
            query.setParameter("id", request.getId());
        }
        if (request.getUserId() != null) {
            query.setParameter("userId", request.getUserId());
        }
        if (request.getAuthority() != null) {
            query.setParameter("authority", request.getAuthority());
        }
    }
}
