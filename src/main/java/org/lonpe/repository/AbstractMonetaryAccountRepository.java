package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractMonetaryAccountRepository implements CrudRepository<MonetaryAccount, Long> {

    private final EntityManager entityManager;

    AbstractMonetaryAccountRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<MonetaryAccount> findById(Long id);

    abstract Optional<MonetaryAccount> findByPkey(String pkey);

}
