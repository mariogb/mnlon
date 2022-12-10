package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractDepartamentRepository implements CrudRepository<Departament, Long> {

    private final EntityManager entityManager;

    AbstractDepartamentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<Departament> findById(Long id);

    abstract Optional<Departament> findByPkey(String pkey);

}
