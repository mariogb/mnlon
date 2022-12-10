package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractProgramJobRepository implements CrudRepository<ProgramJob, Long> {

    private final EntityManager entityManager;

    AbstractProgramJobRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<ProgramJob> findById(Long id);

    abstract Optional<ProgramJob> findByPkey(String pkey);

}
