package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractDepartamentBaseTimePeriodRepository implements CrudRepository<DepartamentBaseTimePeriod, Long> {

    private final EntityManager entityManager;

    AbstractDepartamentBaseTimePeriodRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<DepartamentBaseTimePeriod> findById(Long id);

    abstract Optional<DepartamentBaseTimePeriod> findByPkey(String pkey);

}
