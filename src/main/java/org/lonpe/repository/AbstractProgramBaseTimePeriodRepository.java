package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractProgramBaseTimePeriodRepository implements CrudRepository<ProgramBaseTimePeriod, Long> {

    private final EntityManager entityManager;

    AbstractProgramBaseTimePeriodRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<ProgramBaseTimePeriod> findById(Long id);

    abstract Optional<ProgramBaseTimePeriod> findByPkey(String pkey);

}
