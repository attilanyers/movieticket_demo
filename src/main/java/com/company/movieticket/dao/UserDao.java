package com.company.movieticket.dao;

import com.company.movieticket.dto.ListUserRequest;
import com.company.movieticket.entity.UserEntity;
import com.company.movieticket.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends Dao<UserEntity, UUID, UserRepository> {
    public UserDao(EntityManager em, UserRepository repository) {
        super(em, repository, UserEntity.class);
    }

    public Page<UserEntity> listUser(ListUserRequest request) {
        // sql: SELECT e FROM UserEntity e WHERE (e.id = :id) AND (e.email ILIKE CONCAT('%', :email,
        // '%'))
        Map<String, String> sortingOptions = Map.of("id", "e.id", "email", "e.email");
        Set<String> sortingCanStartWith = Set.of("id", "email");
        validateSort(request.getSort(), sortingOptions, sortingCanStartWith);

        SqlBuilder builder = new SqlBuilder();

        if (request.getId() != null) {
            builder.addWhereCondition("e.id = :id");
        }
        if (request.getEmail() != null) {
            builder.addWhereCondition("e.email ILIKE CONCAT('%', :email, '%')");
        }

        String countStatement = builder.getQuery("SELECT COUNT(e) FROM UserEntity e");
        String selectEntitiesStatement =
                builder.getQuery("SELECT e FROM UserEntity e", request.getSort(), sortingOptions);
        TypedQuery<Long> countQuery = em.createQuery(countStatement, Long.class);
        listUserBindParameters(countQuery, request);
        long count = countQuery.getSingleResult();
        int firstResult = request.getPageIndex() * request.getPageSize();
        TypedQuery<UserEntity> selectEntitiesQuery =
                em.createQuery(selectEntitiesStatement, UserEntity.class);
        selectEntitiesQuery.setFirstResult(firstResult);
        selectEntitiesQuery.setMaxResults(request.getPageSize());
        listUserBindParameters(selectEntitiesQuery, request);
        List<UserEntity> names = selectEntitiesQuery.getResultList();
        return new PageImpl(
                names, PageRequest.of(request.getPageIndex(), request.getPageSize()), count);
    }

    public Optional<UserEntity> findByEmailFetchAuthorities(String email) {
        return repository.findByEmailFetchAuthorities(email);
    }

    public Optional<UserEntity> findByIdFetchAuthorities(UUID id) {
        return repository.findByIdFetchAuthorities(id);
    }

    private void listUserBindParameters(TypedQuery query, ListUserRequest request) {
        if (request.getId() != null) {
            query.setParameter("id", request.getId());
        }
        if (request.getEmail() != null) {
            query.setParameter("email", request.getEmail());
        }
    }
}
