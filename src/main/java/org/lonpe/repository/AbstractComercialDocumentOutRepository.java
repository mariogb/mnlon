package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractComercialDocumentOutRepository implements CrudRepository<ComercialDocumentOut, Long> {

    private final EntityManager entityManager;

    AbstractComercialDocumentOutRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<ComercialDocumentOut> findById(Long id);

    abstract Optional<ComercialDocumentOut> findByPkey(String pkey);

}
