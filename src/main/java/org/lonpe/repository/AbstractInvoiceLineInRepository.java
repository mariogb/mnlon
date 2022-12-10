package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractInvoiceLineInRepository implements CrudRepository<InvoiceLineIn, Long> {

    private final EntityManager entityManager;

    AbstractInvoiceLineInRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<InvoiceLineIn> findById(Long id);

    abstract Optional<InvoiceLineIn> findByPkey(String pkey);

}
