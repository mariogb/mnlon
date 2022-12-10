package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractFligthRepository implements CrudRepository<Fligth, Long> {

    private final EntityManager entityManager;

    AbstractFligthRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<Fligth> findById(Long id);

    abstract Optional<Fligth> findByPkey(String pkey);

}
