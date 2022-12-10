package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractFormLonRepository implements CrudRepository<FormLon, Long> {

    private final EntityManager entityManager;

    AbstractFormLonRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<FormLon> findById(Long id);

    abstract Optional<FormLon> findByPkey(String pkey);

}
