package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractPaymentOutTypeRepository implements CrudRepository<PaymentOutType, Long> {

    private final EntityManager entityManager;

    AbstractPaymentOutTypeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<PaymentOutType> findById(Long id);

    abstract Optional<PaymentOutType> findByPkey(String pkey);

}
