package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractWorkSpaceRepository implements CrudRepository<WorkSpace, Long> {

    private final EntityManager entityManager;

    AbstractWorkSpaceRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<WorkSpace> findById(Long id);

    abstract Optional<WorkSpace> findByPkey(String pkey);

}
