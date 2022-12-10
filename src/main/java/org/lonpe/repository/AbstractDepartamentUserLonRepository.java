package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractDepartamentUserLonRepository implements CrudRepository<DepartamentUserLon, Long> {

    private final EntityManager entityManager;

    AbstractDepartamentUserLonRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<DepartamentUserLon> findById(Long id);

    abstract Optional<DepartamentUserLon> findByPkey(String pkey);

}
