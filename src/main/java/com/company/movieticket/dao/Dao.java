package com.company.movieticket.dao;

import com.company.movieticket.dto.Order;
import com.company.movieticket.exception.BaseException;
import com.company.movieticket.exception.ErrorEnum;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnitUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class Dao<
        ENTITY,
        ID,
        REPOSITORY extends JpaSpecificationExecutor<ENTITY> & JpaRepository<ENTITY, ID>> {
    protected final EntityManager em;
    protected final REPOSITORY repository;

    protected final Class<ENTITY> entityClass;

    public Dao(EntityManager em, REPOSITORY repository, Class<ENTITY> entityClass) {
        this.em = em;
        this.repository = repository;
        this.entityClass = entityClass;
    }

    public ENTITY findById(ID id) {
        Optional<ENTITY> entity = repository.findById(id);
        return getOrThrow(entity);
    }

    public Page<ENTITY> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<ENTITY> findAll() {
        return repository.findAll();
    }

    public List<ENTITY> findAllById(Iterable<ID> ids) {
        return repository.findAllById(ids);
    }

    protected ENTITY getOrThrow(Optional<ENTITY> entityOpt) {
        return entityOpt.orElseThrow(
                () -> new BaseException(ErrorEnum.ENTITY_NOT_FOUND, "Cannot find entity"));
    }

    @Transactional
    public ENTITY save(ENTITY entity) {
        return repository.save(entity);
    }

    @Transactional
    public void delete(ENTITY entity) {
        repository.delete(entity);
    }

    @Transactional
    public void delete(Iterable<ENTITY> entities) {
        repository.deleteAll(entities);
    }

    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    protected Sort createSort(List<Order> sorting) {
        if (sorting == null || sorting.isEmpty()) {
            return Sort.unsorted();
        }
        return Sort.by(
                sorting.stream()
                        .map(
                                order ->
                                        new Sort.Order(
                                                Sort.Direction.fromString(
                                                        order.getDirection().name()),
                                                order.getProperty()))
                        .collect(Collectors.toList()));
    }

    protected void validateSort(
            List<Order> sort,
            java.util.Map<String, String> sortingOptions,
            java.util.Set<String> sortingCanStartWith) {
        if (sort != null && !sort.isEmpty()) {
            Order first = sort.get(0);
            if (!sortingCanStartWith.contains(first.getProperty())) {
                throw new BaseException(
                        ErrorEnum.BAD_REQUEST,
                        "Sorting cannot start with property: " + first.getProperty());
            }
            java.util.Optional<Order> missingOrder =
                    sort.stream()
                            .filter(order -> !sortingOptions.containsKey(order.getProperty()))
                            .findFirst();
            missingOrder.ifPresent(
                    order -> {
                        throw new BaseException(
                                ErrorEnum.BAD_REQUEST,
                                "Illegal sorting property: " + order.getProperty());
                    });
        }
    }
}
