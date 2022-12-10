package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractComercialDocumentTypeOutRepository implements CrudRepository<ComercialDocumentTypeOut, Long> {

    private final EntityManager entityManager;

    AbstractComercialDocumentTypeOutRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<ComercialDocumentTypeOut> findById(Long id);

    abstract Optional<ComercialDocumentTypeOut> findByPkey(String pkey);

}
