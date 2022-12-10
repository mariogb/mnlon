package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractUserRoleRepository implements CrudRepository<UserRole, Long> {

    private final EntityManager entityManager;

    AbstractUserRoleRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<UserRole> findById(Long id);

    abstract Optional<UserRole> findByPkey(String pkey);

}
