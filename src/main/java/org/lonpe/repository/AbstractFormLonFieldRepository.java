package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractFormLonFieldRepository implements CrudRepository<FormLonField, Long> {

    private final EntityManager entityManager;

    AbstractFormLonFieldRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<FormLonField> findById(Long id);

    abstract Optional<FormLonField> findByPkey(String pkey);

}
