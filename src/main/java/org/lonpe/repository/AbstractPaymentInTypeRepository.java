package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractPaymentInTypeRepository implements CrudRepository<PaymentInType, Long> {

    private final EntityManager entityManager;

    AbstractPaymentInTypeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<PaymentInType> findById(Long id);

    abstract Optional<PaymentInType> findByPkey(String pkey);

}
