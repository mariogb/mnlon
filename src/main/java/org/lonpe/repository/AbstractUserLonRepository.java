package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractUserLonRepository implements CrudRepository<UserLon, Long> {

    private final EntityManager entityManager;

    AbstractUserLonRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<UserLon> findById(Long id);

    abstract Optional<UserLon> findByPkey(String pkey);

}
