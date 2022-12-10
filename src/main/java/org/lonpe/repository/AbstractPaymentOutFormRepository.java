package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractPaymentOutFormRepository implements CrudRepository<PaymentOutForm, Long> {

    private final EntityManager entityManager;

    AbstractPaymentOutFormRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<PaymentOutForm> findById(Long id);

    abstract Optional<PaymentOutForm> findByPkey(String pkey);

}
