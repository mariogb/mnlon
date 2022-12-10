package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractBaseTimePeriodRepository implements CrudRepository<BaseTimePeriod, Long> {

    private final EntityManager entityManager;

    AbstractBaseTimePeriodRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<BaseTimePeriod> findById(Long id);

    abstract Optional<BaseTimePeriod> findByPkey(String pkey);

}
