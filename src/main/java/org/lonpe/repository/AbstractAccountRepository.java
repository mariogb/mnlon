package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractAccountRepository implements CrudRepository<Account, Long> {

    private final EntityManager entityManager;

    AbstractAccountRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<Account> findById(Long id);

    abstract Optional<Account> findByPkey(String pkey);

}
