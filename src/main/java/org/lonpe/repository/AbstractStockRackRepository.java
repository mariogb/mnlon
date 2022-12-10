package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractStockRackRepository implements CrudRepository<StockRack, Long> {

    private final EntityManager entityManager;

    AbstractStockRackRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<StockRack> findById(Long id);

    abstract Optional<StockRack> findByPkey(String pkey);

}
