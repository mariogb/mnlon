package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractContractOutRepository implements CrudRepository<ContractOut, Long> {

    private final EntityManager entityManager;

    AbstractContractOutRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<ContractOut> findById(Long id);

    abstract Optional<ContractOut> findByPkey(String pkey);

}
