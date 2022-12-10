package org.lonpe.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import javax.persistence.EntityManager;
import java.util.List;
import org.lonpe.model.*;
import java.util.Optional;

@Repository
public abstract class AbstractAppointmentRepository implements CrudRepository<Appointment, Long> {

    private final EntityManager entityManager;

    AbstractAppointmentRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Optional<Appointment> findById(Long id);

    abstract Optional<Appointment> findByPkey(String pkey);

}
