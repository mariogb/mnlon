package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractPaymentInRepository implements CrudRepository<PaymentIn, Long> {

    private final EntityManager entityManager;

    AbstractPaymentInRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<PaymentIn> findById(Long id);

    abstract Optional<PaymentIn> findByPkey(String pkey);

}
